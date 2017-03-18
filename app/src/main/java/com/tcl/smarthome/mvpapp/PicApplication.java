package com.tcl.smarthome.mvpapp;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import com.tcl.smarthome.mvpapp.api.TianGouPicApi;
import com.tcl.smarthome.mvpapp.api.TianGouPicApiInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: 应用程序总入口
 * Auther: 陈占洋
 * Data: 2017/3/16.
 */

public class PicApplication extends Application {

    public static final String TAG = "chen_zhanyang";

    private OkHttpClient mHttpClient;
    private static Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        mHttpClient = buildHttpClient();
        mRetrofit = buildRetrofit();
    }

    private OkHttpClient buildHttpClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String username = chain.request().headers().get("username");
                        String password = chain.request().headers().get("password");
                        String basic = Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);
                        Request request = chain.request().newBuilder().addHeader("Authorization", "Basic " + basic).build();
                        Log.i(TAG, "intercept: 请求头=="+request.headers().toString());
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }

    /**
     * 构建Retrofit
     */
    private Retrofit buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TianGouPicApiInfo.TIAN_GOU_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(mHttpClient)
                .build();
        return retrofit;
    }

    /**
     * 获取图片API
     * @return
     */
    public static TianGouPicApi getApi(){
        if (mRetrofit != null){
            return mRetrofit.create(TianGouPicApi.class);
        }else {
            throw new RuntimeException("Retrofit 初始化失败");
        }
    }
}
