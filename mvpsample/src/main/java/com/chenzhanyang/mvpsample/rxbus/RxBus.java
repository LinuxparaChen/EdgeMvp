package com.chenzhanyang.mvpsample.rxbus;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureError;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/4.
 */

public class RxBus {

    private Subject<Object> mSubjectBus;
    private FlowableProcessor<Object> mProcessorBus;

    private RxBus(){
        mSubjectBus = PublishSubject.create().toSerialized();
        mProcessorBus = PublishProcessor.create().toSerialized();
    }
    private static volatile RxBus sRxBus;
    public static RxBus getInstance(){
        if (sRxBus == null){
            synchronized (RxBus.class){
                if (sRxBus == null){
                    sRxBus = new RxBus();
                }
            }
        }
        return sRxBus;
    }

    public Disposable register(Class eventType, Consumer observer) {
        return toObserverable(eventType).subscribe(observer);
    }

    public Disposable register(Class eventType, Consumer observer, Scheduler scheduler) {
        return toObserverable(eventType).observeOn(scheduler).subscribe(observer);
    }
    public Disposable register(Class eventType, Consumer observer,Scheduler scheduler, BackpressureStrategy strategy){
        Flowable o = toFlowable(eventType);
        switch (strategy) {
            case DROP:
                o = o.onBackpressureDrop();
            case LATEST:
                o = o.onBackpressureLatest();
            case MISSING:
                o = o;
            case ERROR:
                o = RxJavaPlugins.onAssembly(new FlowableOnBackpressureError<>(o));
            default:
                o = o.onBackpressureBuffer();
        }
        if(scheduler!=null){
            o.observeOn(scheduler);
        }
        return o.subscribe(observer);
    }
    public Disposable register(Class eventType, Consumer observer,BackpressureStrategy strategy){
        return register(eventType,observer,null,strategy);
    }
    public void unRegister(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
    public void unRegister(CompositeDisposable compositeDisposable) {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
    public void post(final Object event) {
        mSubjectBus.onNext(event);
        mProcessorBus.onNext(event);
    }
    private Observable toObserverable(Class cls) {
        return mSubjectBus.ofType(cls);
    }
    private Flowable toFlowable(Class cls) {
        return mProcessorBus.ofType(cls);
    }
    public boolean hasObservers() {
        return mSubjectBus.hasObservers();
    }
    public boolean hasSubscribers() {
        return mProcessorBus.hasSubscribers();
    }

}
