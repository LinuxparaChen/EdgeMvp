package com.chenzhanyang.mvpsample.presenter;

import com.chenzhanyang.mvpsample.model.PicListModel;

import org.junit.Test;

/**
 * Description: 测试图片列表
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/5.
 */
public class PicListPresenterTest {

    @Test
    public void testPicList(){
        PicListPresenter picListPresenter = new PicListPresenter(PicListModel.class);
        picListPresenter.getPicList(1,1);
    }

}