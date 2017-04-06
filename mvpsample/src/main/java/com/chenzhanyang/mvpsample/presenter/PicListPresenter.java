package com.chenzhanyang.mvpsample.presenter;

import com.chenzhanyang.mvpsample.model.PicListModel;
import com.chenzhanyang.mvpsample.model.bean.PicListBean;
import com.chenzhanyang.mvpsample.view.fragment.PicListFragment;
import com.tcl.smarthome.mvplibrary.presenter.HttpPresenter;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/5.
 */

public class PicListPresenter extends HttpPresenter<PicListModel,PicListBean,PicListFragment>{
    public PicListPresenter(Class clazz) {
        super(clazz);
    }

    public void getPicList(int page,int id){
        mModel.getPicList(page,id);
    }
}
