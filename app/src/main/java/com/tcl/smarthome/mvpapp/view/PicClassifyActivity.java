package com.tcl.smarthome.mvpapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.internal.$Gson$Types;
import com.tcl.smarthome.mvpapp.R;
import com.tcl.smarthome.mvpapp.adapter.PicClassifyAdapter;
import com.tcl.smarthome.mvpapp.interfaces.OnItemClickListener;
import com.tcl.smarthome.mvpapp.model.PicClassifyModel;
import com.tcl.smarthome.mvpapp.model.bean.PicClassifyBean;
import com.tcl.smarthome.mvplibrary.presenter.HttpPresenter;
import com.tcl.smarthome.mvplibrary.view.IHttpView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片分类界面
 */
public class PicClassifyActivity extends AppCompatActivity implements IHttpView<PicClassifyBean>,OnItemClickListener{

    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;

    private HttpPresenter<PicClassifyModel, PicClassifyActivity> mHttpPresenter;
    private List<PicClassifyBean.TngouBean> mData;
    private PicClassifyAdapter mPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mPicAdapter = new PicClassifyAdapter(mData);
        mPicAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mPicAdapter);
    }

    /**
     * 初始化
     */
    private void init() {
        ButterKnife.bind(this);
        mData = new ArrayList<>();

        mHttpPresenter = new HttpPresenter<>(PicClassifyModel.class);

        Type superclassType = mHttpPresenter.getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) superclassType).getActualTypeArguments();
        Log.i("chen_zhanyang", "init: types = "+ Arrays.toString(types));

        mHttpPresenter.attach(this);

        mHttpPresenter.fetchData();
    }

    @Override
    public void onHttpStart() {
        Log.i("chen_zhanyang", "onHttpStart: ");
    }

    @Override
    public void onHttpSuccess(PicClassifyBean picClassifyBean) {
        Log.i("chen_zhanyang", "onHttpSuccess: ");
        mPicAdapter.setData(picClassifyBean.getTngou());
    }

    @Override
    public void onHttpFaild(Throwable t) {
        Log.i("chen_zhanyang", "onHttpFaild: ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHttpPresenter.dettach();
    }

    /**
     * 列表条目被点击监听方法
     * @param view
     */
    @Override
    public void onItemClick(View view,int position) {
        List<PicClassifyBean.TngouBean> data = mPicAdapter.getData();
        int id = data.get(position).getId();
        Intent intent = new Intent(this, PicListActivity.class);

        startActivity(intent);
    }
}
