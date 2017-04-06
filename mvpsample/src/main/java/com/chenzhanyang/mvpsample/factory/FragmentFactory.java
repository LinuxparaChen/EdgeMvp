package com.chenzhanyang.mvpsample.factory;

import android.support.v4.app.Fragment;

import com.chenzhanyang.mvpsample.view.fragment.PicListFragment;
import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/4.
 */

public class FragmentFactory {

    private static Map<Integer,Fragment> mFragmentCache = new HashMap<>();

    public static Fragment createFragment(PicClassifyBean.TngouBean tngouBean) {
        int id = tngouBean.getId();
        Fragment fragment = mFragmentCache.get(id);
        if (fragment == null){
            fragment = new PicListFragment();
            mFragmentCache.put(id,fragment);
        }
//        RxBus.getInstance().post(tngouBean);
        return fragment;
    }
}
