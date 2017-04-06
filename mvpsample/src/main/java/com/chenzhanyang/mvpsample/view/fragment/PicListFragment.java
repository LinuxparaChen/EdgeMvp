package com.chenzhanyang.mvpsample.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenzhanyang.mvpsample.R;
import com.chenzhanyang.mvpsample.adapter.PicListAdapter;
import com.chenzhanyang.mvpsample.model.PicListModel;
import com.chenzhanyang.mvpsample.model.bean.PicClassifyBean;
import com.chenzhanyang.mvpsample.model.bean.PicListBean;
import com.chenzhanyang.mvpsample.presenter.PicListPresenter;
import com.tcl.smarthome.mvplibrary.view.IHttpView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/4.
 */

public class PicListFragment extends Fragment implements IHttpView<PicListBean> {
    private static final String TAG = "PicListFragment";

    @BindView(R.id.fragment_piclist_rv)
    RecyclerView mRecyclerView;

    private View mRootView;
    private PicClassifyBean.TngouBean mTitleBean;
    private PicListPresenter mPicListPresenter;
    private int mCurrentPage = 1;
    private PicListAdapter mPicListAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPicListPresenter = new PicListPresenter(PicListModel.class);
        mPicListPresenter.attach(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitleBean = (PicClassifyBean.TngouBean) bundle.getSerializable("title");
            if (mTitleBean != null) {
                //请求网络获取图片列表
                mPicListPresenter.getPicList(mCurrentPage, mTitleBean.getId());
            } else {
                Log.i(TAG, "onCreate: mTitleBean===null");
            }
        } else {
            Log.i(TAG, "onCreate: bundle===null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_pic, container, false);
        ButterKnife.bind(this, mRootView);

        init();
        return mRootView;
    }

    private void init() {
        mPicListAdapter = new PicListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mPicListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPicListPresenter.dettach();
    }

    @Override
    public void onHttpStart() {
        Log.i(TAG, "onHttpStart: ");
    }

    @Override
    public void onHttpSuccess(PicListBean picListBean) {
        Log.i(TAG, "onHttpSuccess: ");
        if (picListBean.isStatus()) {
            List<PicListBean.TngouBean> data = picListBean.getTngou();
            mPicListAdapter.setData(data);
        } else {
            Log.i(TAG, "onHttpSuccess: status == false");
        }
    }

    @Override
    public void onHttpFaild(Throwable t) {
        Log.i(TAG, "onHttpFaild: " + t.getMessage());
    }
}
