package com.power.parts.base.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;


import org.simple.eventbus.EventBus;

/**
 * Created by Administrator on 2018/11/5.
 * 每个 {@link Activity} 都需要实现此类,以满足规范
 */
public interface IActivity {
    /**
     * 绑定Presenter
     */
    IPresenter bindPresenter();


    /**
     * 是否使用 {@link EventBus}
     *
     * @return
     */
    boolean useEventBus();

    /**
     * 初始化 View, 如果 {@link #initView()} 返回 0, 框架则不会调用 {@link Activity#setContentView(int)}
     *
     * @return
     */
    int initView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);

}
