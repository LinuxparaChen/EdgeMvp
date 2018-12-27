package com.linuxpara.edgemvp_example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.linuxpara.edgemvp_annotation.ExtractItf;
import com.linuxpara.edgemvp_annotation.MvpView;
import com.linuxpara.edgemvp.EdgeMvp;
import com.linuxpara.edgemvp_example.R;
import com.linuxpara.edgemvp_example.login.presenter.login.ILoginPresenter;

@MvpView(key = "Login", presenter = LoginPresenter.class)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ILoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = EdgeMvp.createPresenter(this);
        mPresenter.login("test", "12345");

        findViewById(R.id.login_btn_login).setOnClickListener(this);
    }

    @ExtractItf
    public void showLoginSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @ExtractItf
    public void showLoginFailed(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn_login:
                mPresenter.login("test","123456");
                break;

        }
    }
}
