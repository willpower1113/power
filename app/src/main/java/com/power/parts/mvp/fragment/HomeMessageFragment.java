package com.power.parts.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.power.parts.R;
import com.power.parts.base.BaseFragment;
import com.power.parts.base.delegate.IPresenter;

/**
 * Created by Administrator on 2018/11/5.
 */

public class HomeMessageFragment extends BaseFragment{
    @Override
    public int initView() {
        return R.layout.fragment_home_message;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public IPresenter bindPresenter() {
        return null;
    }
}
