package com.power.parts.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.power.parts.R;
import com.power.parts.base.delegate.ActivityLifecycleable;
import com.power.parts.base.delegate.IActivity;
import com.power.parts.base.delegate.IPresenter;
import com.power.parts.util.StatusBarUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2018/11/5.
 * Activity基类
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, ActivityLifecycleable {
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    private Unbinder mUnbinder;
    protected Context context;
    protected Activity activity;

    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor();
        try {
            int layoutResID = initView();
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定到ButterKnife
                mUnbinder = ButterKnife.bind(this);
            }
            if (useEventBus())//如果要使用 EventBus 请将此方法返回 true
            {
                EventBus.getDefault().register(this);//注册 EventBus
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.context = this;
        this.activity = this;
        mPresenter = bindPresenter();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {//如果要使用 EventBus 请将此方法返回 true
            EventBus.getDefault().unregister(this);//解除注册 EventBus
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }
        this.mPresenter = null;
    }

    /**
     * 绑定Presenter
     */
    public abstract P bindPresenter();

    /**
     * 是否使用eventBus,默认为使用(false)，
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return false;
    }

    /**
     * 重写StatusBar颜色
     */
    public void setStatusBarColor() {
    }


    /**
     * 显示加载
     */
    public void showLoading() {

    }


    /**
     * 隐藏加载
     */
    public void hideLoading() {

    }


    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 {@code null}
     */
    public void showMessage(@NonNull String message) {

    }


    /**
     * 跳转 {@link Activity}
     *
     * @param intent {@code intent} 不能为 {@code null}
     */
    public void launchActivity(@NonNull Intent intent) {

    }


    /**
     * 杀死自己
     */
    public void killMyself() {

    }

}
