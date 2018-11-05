package com.power.parts.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.power.parts.R;
import com.power.parts.base.BaseActivity;
import com.power.parts.base.adapter.BaseFragmentStatePagerAdapter;
import com.power.parts.mvp.fragment.HomeControlFragment;
import com.power.parts.mvp.fragment.HomeMessageFragment;
import com.power.parts.mvp.fragment.HomePageFragment;
import com.power.parts.mvp.model.HomePageModel;
import com.power.parts.mvp.presenter.HomePagePresenter;
import com.power.parts.mvp.view.HomePageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomePageActivity extends BaseActivity<HomePagePresenter> implements HomePageView {
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    @BindView(R.id.tab_home)
    TabLayout tabHome;

    BaseFragmentStatePagerAdapter statePagerAdapter;

    List<Fragment> fragmentList;

    HomePageFragment pageFragment;
    HomeMessageFragment msgFragment;
    HomeControlFragment controlFragment;

    final CharSequence[] mTitles = {"首页", "消息", "我的"};

    final int[] mTabImages = {R.drawable.selector_home_tab1, R.drawable.selector_home_tab1, R.drawable.selector_home_tab1};

    @Override
    public HomePagePresenter bindPresenter() {
        return new HomePagePresenter(this, new HomePageModel());
    }

    @Override
    public int initView() {
        return R.layout.activity_home_page;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initViewPager();
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>(3);
        pageFragment = new HomePageFragment();
        msgFragment = new HomeMessageFragment();
        controlFragment = new HomeControlFragment();

        fragmentList.add(pageFragment);
        fragmentList.add(msgFragment);
        fragmentList.add(controlFragment);

        statePagerAdapter = new BaseFragmentStatePagerAdapter(getSupportFragmentManager(), fragmentList, mTitles);
        vpHome.setAdapter(statePagerAdapter);
        tabHome.setupWithViewPager(vpHome);

        for (int i = 0; i < fragmentList.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_home_tab, null);
            ((TextView) view.findViewById(R.id.tv_home_tab)).setText(mTitles[i]);
            ((ImageView) view.findViewById(R.id.img_home_tab)).setImageResource(mTabImages[i]);
            tabHome.getTabAt(i).setCustomView(view);
        }
    }
}
