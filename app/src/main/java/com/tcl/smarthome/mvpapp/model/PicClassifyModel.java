package com.tcl.smarthome.mvpapp.model;

import android.util.Log;

import com.tcl.smarthome.mvpapp.PicApplication;
import com.tcl.smarthome.mvpapp.api.TianGouPicApi;
import com.tcl.smarthome.mvpapp.model.bean.PicClassifyBean;
import com.tcl.smarthome.mvplibrary.model.HttpModel;
import com.tcl.smarthome.mvplibrary.presenter.HttpPresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 图片分类 Model层
 * Auther: 陈占洋
 * Email: zhanyang.chen@gmail.com
 * Data: 2017/3/17.
 */

public class PicClassifyModel extends HttpModel<HttpPresenter>{

    @Override
    public void fetchData(Object... params) {
        getPicClassifyData();
    }

    private void getPicClassifyData() {
        TianGouPicApi picApi = PicApplication.getApi();
        picApi.getClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mPresenter.onHttpStart();
                    }

                    @Override
                    public void onNext(PicClassifyBean value) {
                        Log.i("chen_zhanyang", "onNext: "+value);
                        mPresenter.onHttpSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("chen_zhanyang", "onError: "+e.getMessage());
                        mPresenter.onHttpFaild(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
