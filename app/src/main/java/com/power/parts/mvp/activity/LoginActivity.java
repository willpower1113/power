package com.power.parts.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.power.parts.R;
import com.power.parts.base.BaseActivity;
import com.power.parts.mvp.model.LoginModel;
import com.power.parts.mvp.presenter.LoginPresenter;
import com.power.parts.mvp.view.LoginView;

/**
 * Created by Administrator on 2018/11/5.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Override
    public int initView() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public LoginPresenter bindPresenter() {
        return new LoginPresenter(this, new LoginModel());
    }


    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFail() {

    }
}
