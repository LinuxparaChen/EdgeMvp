package com.chenzhanyang.mvpsample.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chenzhanyang.mvpsample.factory.FragmentFactory;
import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;

import java.util.List;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<PicClassifyBean.TngouBean> mTitles;

    public ViewPagerAdapter(FragmentManager fm,List<PicClassifyBean.TngouBean> titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        PicClassifyBean.TngouBean tngouBean = mTitles.get(position);
        Fragment fragment = FragmentFactory.createFragment(tngouBean);
        Bundle bundle = new Bundle();
        bundle.putSerializable("title",tngouBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getTitle();
    }

    public void setData(List<PicClassifyBean.TngouBean> titles) {
        this.mTitles = titles;
        this.notifyDataSetChanged();
    }
}
