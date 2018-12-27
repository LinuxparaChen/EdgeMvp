package com.linuxpara.edgemvp_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.linuxpara.edgemvp_example.list.ListActivity;
import com.linuxpara.edgemvp_example.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_btn_login).setOnClickListener(this);
        findViewById(R.id.main_btn_list).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_login:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.main_btn_list:
                Intent listIntent = new Intent(this, ListActivity.class);
                startActivity(listIntent);
                break;
        }
    }
}
