package com.power.parts.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.power.parts.R;
import com.power.parts.base.BaseActivity;
import com.power.parts.base.adapter.BaseFragmentStatePagerAdapter;
import com.power.parts.base.delegate.IPresenter;
import com.power.parts.mvp.fragment.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/11/5.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tab_login)
    TabLayout tabLogin;
    @BindView(R.id.vp_login)
    ViewPager vpLogin;

    BaseFragmentStatePagerAdapter viewPagerAdapter;

    private CharSequence[] mTitles = {"注册", "登录"};

    private List<Fragment> mList;

    private LoginFragment loginFragment;

    @Override
    public int initView() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initViewPager();
    }

    @Override
    public IPresenter bindPresenter() {
        return null;
    }

    private void initViewPager() {
        mList = new ArrayList<>(2);
        loginFragment = new LoginFragment();
        loginFragment.setData(LoginFragment.TAG_LOGIN);
        mList.add(loginFragment);

        loginFragment = new LoginFragment();
        loginFragment.setData(LoginFragment.TAG_REGISTER);
        mList.add(loginFragment);

        viewPagerAdapter = new BaseFragmentStatePagerAdapter(getSupportFragmentManager(), mList);
        vpLogin.setAdapter(viewPagerAdapter);
        tabLogin.setupWithViewPager(vpLogin);
        tabLogin.getTabAt(0).setText(mTitles[0]);
        tabLogin.getTabAt(1).setText(mTitles[1]);
    }
}
