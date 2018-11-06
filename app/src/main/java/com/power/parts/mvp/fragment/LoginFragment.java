package com.power.parts.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.parts.R;
import com.power.parts.base.BaseFragment;
import com.power.parts.base.OnNextListener;
import com.power.parts.mvp.model.LoginModel;
import com.power.parts.mvp.presenter.LoginPresenter;
import com.power.parts.mvp.view.LoginView;
import com.power.parts.util.RegexUtils;
import com.qmuiteam.qmui.util.QMUIViewHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/5.
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView {
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_pwd_login)
    TextView tvPwdLogin;
    @BindView(R.id.img_qq_login)
    ImageView imgQqLogin;
    @BindView(R.id.img_wx_login)
    ImageView imgWxLogin;

    public static final int TAG_LOGIN = 2;
    public static final int TAG_REGISTER = 1;

    private int type = TAG_REGISTER;

    @Override
    public int initView() {
        return R.layout.fragment_login_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void setData(@Nullable Object data) {
        if (data instanceof Integer) {
            this.type = (int) data;
        }
    }

    @Override
    public LoginPresenter bindPresenter() {
        return new LoginPresenter(this, new LoginModel());
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (type == TAG_REGISTER) {
            mPresenter.login();
        } else if (type == TAG_LOGIN) {
            mPresenter.login();
        }
    }

   void doService(OnNextListener listener) {
        String phone = edPhone.getText().toString();
        String password = edPassword.getText().toString();
        if (!RegexUtils.checkPhone(phone)){
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("phone", edPhone.getText().toString().trim());
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFail() {

    }

}
