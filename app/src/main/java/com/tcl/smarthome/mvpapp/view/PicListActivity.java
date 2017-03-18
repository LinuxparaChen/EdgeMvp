package com.tcl.smarthome.mvpapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tcl.smarthome.mvpapp.R;
import com.tcl.smarthome.mvplibrary.view.IHttpView;

/**
 * 图片列表界面
 */
public class PicListActivity extends AppCompatActivity implements IHttpView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_list);

        init();
    }

    private void init() {

    }

    @Override
    public void onHttpStart() {

    }

    @Override
    public void onHttpSuccess(Object o) {

    }

    @Override
    public void onHttpFaild(Throwable t) {

    }
}
