package com.chenzhanyang.mvpsample.model;

import android.util.Log;

import com.chenzhanyang.mvpsample.MainApp;
import com.chenzhanyang.mvpsample.api.TianGouApi;
import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;
import com.chenzhanyang.mvpsample.presenter.MainPresenter;
import com.tcl.smarthome.mvplibrary.model.HttpModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */

public class PicClassifyModel extends HttpModel<MainPresenter>{

    private static final String TAG = "PicClassifyModel";

    public void getPicClassify(){

//        TianGouApi tianGouApi = MainApp.getRetrofit().create(TianGouApi.class);
//        Call<PicClassifyBean> picClassifyCall = tianGouApi.getPicClassify();
//        String url = picClassifyCall.request().url().toString();
//        Log.i(TAG, "getPicClassify: url == "+ url);
//        picClassifyCall.enqueue(new Callback<PicClassifyBean>() {
//
//            @Override
//            public void onResponse(Call<PicClassifyBean> call, Response<PicClassifyBean> response) {
//                Log.i(TAG, "onResponse: ");
//            }
//
//            @Override
//            public void onFailure(Call<PicClassifyBean> call, Throwable t) {
//                Log.i(TAG, "onFailure: ");
//            }
//        });

        TianGouApi tianGouApi = MainApp.getTianGouApi();
        tianGouApi.getRxPicClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mPresenter.onHttpStart();
                    }

                    @Override
                    public void onNext(PicClassifyBean picClassifyBean) {
                        mPresenter.onHttpSuccess(picClassifyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.onHttpFaild(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
    }
}
