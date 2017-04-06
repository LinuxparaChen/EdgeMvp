package com.chenzhanyang.mvpsample.presenter;

import com.chenzhanyang.mvpsample.model.PicClassifyModel;
import com.chenzhanyang.mvpsample.model.PicListModel;
import com.chenzhanyang.mvpsample.model.bean.PicListBean;
import com.chenzhanyang.mvpsample.view.fragment.PicListFragment;
import com.tcl.smarthome.mvplibrary.presenter.HttpPresenter;

import org.junit.Test;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */
public class MainPresenterTest {

    @Test
   public void testMainPresenter(){
        MainPresenter mainPresenter = new MainPresenter(PicClassifyModel.class);
        mainPresenter.getPicClassify();
    }

}