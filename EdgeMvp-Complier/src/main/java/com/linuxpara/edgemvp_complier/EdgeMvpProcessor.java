package com.linuxpara.edgemvp_complier;

import com.google.auto.service.AutoService;
import com.linuxpara.edgemvp_annotation.ExtractItf;
import com.linuxpara.edgemvp_annotation.MvpPresenter;
import com.linuxpara.edgemvp_annotation.MvpView;
import com.linuxpara.edgemvp_complier.utils.AnnotationUtil;
import com.linuxpara.edgemvp_complier.utils.JavaFileUtil;
import com.linuxpara.edgemvp_complier.utils.MessagerUtil;
import com.linuxpara.edgemvp_complier.utils.ModifierUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;


@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({
        "com.linuxpara.edgemvp_annotation.MvpView",
        "com.linuxpara.edgemvp_annotation.MvpPresenter",
        "com.linuxpara.edgemvp_annotation.ExtractItf"
})
public class EdgeMvpProcessor extends AbstractProcessor {


    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        mFiler = env.getFiler();

        MessagerUtil.init(env.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {

        Set<? extends Element> elementsView = env.getElementsAnnotatedWith(MvpView.class);

        for (Element element : elementsView) {
            String key = element.getAnnotation(MvpView.class).key();
            String pkg = ((PackageElement) element.getEnclosingElement())
                    .getQualifiedName().toString() + ".view." + key.toLowerCase();

            //生成view接口
            TypeSpec itf = generateVItf((TypeElement) element, key);

            //生成view接口 java文件
            JavaFileUtil.generateJavaFile(pkg, itf, mFiler);

            //生成view代理
            TypeSpec proxy = generateVProxy((TypeElement) element, key, pkg, itf);

            //生成view代理 java文件
            JavaFileUtil.generateJavaFile(pkg, proxy, mFiler);
        }

        Set<? extends Element> elementsPresnter = env.getElementsAnnotatedWith(MvpPresenter.class);

        for (Element element : elementsPresnter) {
            String key = element.getAnnotation(MvpPresenter.class).key();
            String pkg = ((PackageElement) element.getEnclosingElement())
                    .getQualifiedName().toString() + ".presenter." + key.toLowerCase();
            String viewProxyPkg = ((PackageElement) element.getEnclosingElement())
                    .getQualifiedName().toString() + ".view." + key.toLowerCase();

            //生成presnter接口
            TypeSpec itf = generatePItf((TypeElement) element, key);

            //生成presnter接口 java文件
            JavaFileUtil.generateJavaFile(pkg, itf, mFiler);

            //生成presenter代理
            TypeSpec proxy = generatePProxy((TypeElement) element, key, pkg, itf, viewProxyPkg);

            //生成presnter代理 java文件
            JavaFileUtil.generateJavaFile(pkg, proxy, mFiler);

        }
        return true;
    }

    /**
     * 生成View层的接口文件
     *
     * @param element
     * @param key
     * @return
     */
    private TypeSpec generateVItf(TypeElement element, String key) {

        ClassName superClass = ClassName.get("com.linuxpara.edgemvp_annotation.proxy", "EdgeMvpView");

        //构造函数
        MethodSpec constructor_method = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeVariableName.get("V"), "view")
                .addStatement("super($L)", "view")
                .build();

        //抽取的函数
        List<MethodSpec> methods = new ArrayList<>();
        for (Element execEle : element.getEnclosedElements()) {
            if (execEle.getAnnotation(ExtractItf.class) != null) {

                if (!ModifierUtil.isValid(execEle.getModifiers())) {
                    MessagerUtil.error("ExtractItf注解修饰的方法无效。必须 !private、!static、!final、!native。");
                }

                ExecutableElement execElement = (ExecutableElement) execEle;

                List<ParameterSpec> parameters = new ArrayList<>();
                for (VariableElement varEle : execElement.getParameters()) {
                    ParameterSpec parameter = ParameterSpec.builder(TypeName.get(varEle.asType()), varEle.getSimpleName().toString())
                            .build();
                    parameters.add(parameter);
                }

                MethodSpec method = MethodSpec.methodBuilder(execElement.getSimpleName().toString())
                        .addModifiers(execElement.getModifiers())
                        .addModifiers(Modifier.ABSTRACT)
                        .returns(TypeName.get(execElement.getReturnType()))
                        .addParameters(parameters)
                        .build();

                methods.add(method);
            }
        }
        return TypeSpec.classBuilder("I" + key + "View")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addTypeVariable(TypeVariableName.get("V"))
                .superclass(ParameterizedTypeName.get(superClass, TypeVariableName.get("V")))
                .addMethod(constructor_method)
                .addMethods(methods)
                .build();
    }

    /**
     * 生成view层的代理
     *
     * @param element
     * @param key
     * @param pkg
     * @param itf
     */
    private TypeSpec generateVProxy(TypeElement element, String key, String pkg, TypeSpec itf) {

        MethodSpec constructor_method = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(TypeName.get(element.asType()), "view").build())
                .addStatement("super($L)", "view")
                .build();

        List<MethodSpec> methodImpls = new ArrayList<>();
        for (MethodSpec method : itf.methodSpecs) {
            if (!method.isConstructor()) {

                List<Modifier> modifiers = new ArrayList<>();
                for (Modifier modifier : method.modifiers) {
                    if (modifier != Modifier.ABSTRACT) {
                        modifiers.add(modifier);
                    }
                }

                StringBuilder paramVars = new StringBuilder();
                for (ParameterSpec parameter : method.parameters) {
                    paramVars.append(parameter.name);
                    paramVars.append(",");
                }
                paramVars.deleteCharAt(paramVars.length() - 1);

                MethodSpec methodImpl = MethodSpec.methodBuilder(method.name)
                        .addAnnotation(Override.class)
                        .addModifiers(modifiers)
                        .returns(method.returnType)
                        .addParameters(method.parameters)
                        .addCode("try{\n" +
                                "\tgetView().$L($L);\n" +
                                "} catch (Exception ex) {\n" +
                                "\tex.printStackTrace();\n" +
                                "}\n", method.name, paramVars)
                        .build();

                methodImpls.add(methodImpl);
            }
        }

        return TypeSpec.classBuilder(key + "ViewProxy")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get(pkg, itf.name),
                        TypeName.get(element.asType())))
                .addMethod(constructor_method)
                .addMethods(methodImpls)
                .build();
    }

    /**
     * 生成Presenter层的接口
     *
     * @param element
     * @param key
     * @return java文件描述
     */
    private TypeSpec generatePItf(TypeElement element, String key) {

        ClassName superClass = ClassName.get("com.linuxpara.edgemvp_annotation.proxy", "EdgeMvpPresenter");

        ParameterSpec parameter_view = ParameterSpec.builder(TypeVariableName.get("V"), "view").build();
        ParameterSpec parameter_presenter = ParameterSpec.builder(TypeVariableName.get("P"), "presenter").build();
        List<ParameterSpec> constructor_method_parameters = Arrays.asList(parameter_view, parameter_presenter);

        MethodSpec constructor_method = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameters(constructor_method_parameters)
                .addStatement("super($L,$L)", parameter_view.name, parameter_presenter.name)
                .build();

        List<MethodSpec> methods = new ArrayList<>();
        for (Element execEle : element.getEnclosedElements()) {
            if (execEle.getAnnotation(ExtractItf.class) != null) {

                if (!ModifierUtil.isValid(execEle.getModifiers())) {
                    MessagerUtil.error("ExtractItf注解修饰的方法无效。必须 !private、!static、!final、!native。");
                }

                ExecutableElement execElement = (ExecutableElement) execEle;

                List<ParameterSpec> parameters = new ArrayList<>();
                for (VariableElement varEle : execElement.getParameters()) {
                    ParameterSpec parameter = ParameterSpec.builder(TypeName.get(varEle.asType()), varEle.getSimpleName().toString())
                            .build();
                    parameters.add(parameter);
                }

                MethodSpec method = MethodSpec.methodBuilder(execElement.getSimpleName().toString())
                        .addModifiers(execElement.getModifiers())
                        .addModifiers(Modifier.ABSTRACT)
                        .returns(TypeName.get(execElement.getReturnType()))
                        .addParameters(parameters)
                        .build();

                methods.add(method);
            }
        }

        return TypeSpec.classBuilder("I" + key + "Presenter")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addTypeVariables(Arrays.asList(TypeVariableName.get("V"), TypeVariableName.get("P")))
                .superclass(ParameterizedTypeName.get(superClass, TypeVariableName.get("V"), TypeVariableName.get("P")))
                .addMethod(constructor_method)
                .addMethods(methods)
                .build();
    }


    /**
     * 生成Presenter层的代理文件
     *
     * @param element
     * @param key
     * @param pkg
     * @param itf
     * @param viewProxyPkg
     */
    private TypeSpec generatePProxy(TypeElement element, String key, String pkg, TypeSpec itf, String viewProxyPkg) {

        String view_clz = AnnotationUtil.getAnnotationValue(element, MvpPresenter.class, "view");
        if (view_clz == null || "".equals(view_clz)) {
            MessagerUtil.error("%s view 的值不能为空！", element.asType().toString());
        }

        ParameterSpec parameter_view = ParameterSpec.builder(ClassName.bestGuess(view_clz), "view").build();
        ParameterSpec parameter_presenter = ParameterSpec.builder(TypeName.get(element.asType()), "presenter").build();

        MethodSpec constructor_method = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(parameter_view)
                .addParameter(parameter_presenter)
                .addStatement("super($L,$L)", parameter_view.name, parameter_presenter.name)
                .build();

        ClassName rawReturn = ClassName.get("com.linuxpara.edgemvp_annotation.proxy", "EdgeMvpView");

        MethodSpec createViewProxy_method = MethodSpec.methodBuilder("createViewProxy")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PROTECTED)
                .returns(ParameterizedTypeName.get(rawReturn, ClassName.bestGuess(view_clz)))
                .addParameter(ParameterSpec.builder(ClassName.bestGuess(view_clz), "view").build())
                .addStatement("return new $L($L)", ClassName.get(viewProxyPkg, key + "ViewProxy"), "view")
                .build();

        List<MethodSpec> methodImpls = new ArrayList<>();
        for (MethodSpec method : itf.methodSpecs) {
            if (!method.isConstructor()) {

                List<Modifier> modifiers = new ArrayList<>();
                for (Modifier modifier : method.modifiers) {
                    if (modifier != Modifier.ABSTRACT) {
                        modifiers.add(modifier);
                    }
                }

                StringBuilder paramVars = new StringBuilder();
                for (ParameterSpec parameter : method.parameters) {
                    paramVars.append(parameter.name);
                    paramVars.append(",");
                }
                paramVars.deleteCharAt(paramVars.length() - 1);

                MethodSpec methodImpl = MethodSpec.methodBuilder(method.name)
                        .addAnnotation(Override.class)
                        .addModifiers(modifiers)
                        .returns(method.returnType)
                        .addParameters(method.parameters)
                        .addCode("try {\n" +
                                "\tgetPresenter().$L($L);\n" +
                                "} catch (Exception ex) {\n" +
                                "\tex.printStackTrace();\n" +
                                "}", method.name, paramVars)
                        .build();

                methodImpls.add(methodImpl);
            }
        }

        return TypeSpec.classBuilder(key + "PresenterProxy")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get(pkg, itf.name),
                        ClassName.bestGuess(view_clz),
                        TypeName.get(element.asType())))
                .addMethod(constructor_method)
                .addMethod(createViewProxy_method)
                .addMethods(methodImpls)
                .build();
    }
}
