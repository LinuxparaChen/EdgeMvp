package com.chenzhanyang.mvpsample.model;

import com.chenzhanyang.mvpsample.MainApp;
import com.chenzhanyang.mvpsample.api.TianGouApi;
import com.chenzhanyang.mvpsample.model.bean.PicListBean;
import com.chenzhanyang.mvpsample.presenter.PicListPresenter;
import com.tcl.smarthome.mvplibrary.model.HttpModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/5.
 */

public class PicListModel extends HttpModel<PicListPresenter>{
    //请求网络获取图片列表
    public void getPicList(int page,int id){
        TianGouApi tianGouApi = MainApp.getTianGouApi();
        tianGouApi.getRxPicList(page,15,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mPresenter.onHttpStart();
                    }

                    @Override
                    public void onNext(PicListBean picListBean) {
                        mPresenter.onHttpSuccess(picListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.onHttpFaild(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
