package com.chenzhanyang.mvpsample.presenter;

import com.chenzhanyang.mvpsample.model.PicClassifyModel;
import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;
import com.chenzhanyang.mvpsample.view.MainActivity;
import com.tcl.smarthome.mvplibrary.presenter.HttpPresenter;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */

public class MainPresenter extends HttpPresenter<PicClassifyModel,PicClassifyBean,MainActivity>{

    public MainPresenter(Class clazz) {
        super(clazz);
    }

    public void getPicClassify(){
        mModel.getPicClassify();
    }
}
