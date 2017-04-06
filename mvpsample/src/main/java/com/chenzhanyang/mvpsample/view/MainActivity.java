package com.chenzhanyang.mvpsample.view;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.chenzhanyang.mvpsample.R;
import com.chenzhanyang.mvpsample.adapter.ViewPagerAdapter;
import com.chenzhanyang.mvpsample.factory.FragmentFactory;
import com.chenzhanyang.mvpsample.model.PicClassifyModel;
import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;
import com.chenzhanyang.mvpsample.presenter.MainPresenter;
import com.tcl.smarthome.mvplibrary.model.bean.Bean;
import com.tcl.smarthome.mvplibrary.view.IHttpView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/1.
 */
public class MainActivity extends AppCompatActivity implements IHttpView<PicClassifyBean>{

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.main_tablayout)
    TabLayout mTablayout;

    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;

    private static final String TAG = "MainActivity";
    private MainPresenter mMainPresenter;
    private android.support.v4.app.FragmentManager mFm;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenter(PicClassifyModel.class);
        mMainPresenter.attach(this);
        mMainPresenter.getPicClassify();

        init();
    }

    private void init() {
        mToolbar.setTitle("MVPDemo");

        mFm = getSupportFragmentManager();
        mViewPagerAdapter = new ViewPagerAdapter(mFm,null);
        mViewPager.setAdapter(mViewPagerAdapter);

        mTablayout.setupWithViewPager(mViewPager,true);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onHttpStart() {
        Log.i(TAG, "onHttpStart: ");
    }

    @Override
    public void onHttpSuccess(PicClassifyBean picClassifyBean) {

        List<PicClassifyBean.TngouBean> titles = picClassifyBean.getTngou();
        mViewPagerAdapter.setData(titles);
    }

    @Override
    public void onHttpFaild(Throwable t) {
        Log.i(TAG, "onHttpFaild: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.dettach();
    }
}
