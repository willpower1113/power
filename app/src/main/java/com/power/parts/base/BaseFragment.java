package com.power.parts.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.power.parts.base.delegate.FragmentLifecycleable;
import com.power.parts.base.delegate.IFragment;
import com.power.parts.base.delegate.IPresenter;
import com.power.parts.util.LoadingHelper;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Administrator on 2018/11/5.
 * Fragment 基类
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment, FragmentLifecycleable {
    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    protected View view;
    Unbinder unbinder;

    private Dialog loading;
    protected Context context;
    protected Activity activity;

    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null

    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (initView() != 0) {
            Log.e(TAG, "onCreateView: ");
            view = inflater.inflate(initView(), container, false);
            unbinder = ButterKnife.bind(this,view);
        }
        this.context = getContext();
        this.activity = getActivity();
        mPresenter = bindPresenter();
        initData(savedInstanceState);
        if (useEventBus())//如果要使用 EventBus 请将此方法返回 true
        {
            EventBus.getDefault().register(this);//注册 EventBus
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }
        this.mPresenter = null;
        if (useEventBus()) {//如果要使用 EventBus 请将此方法返回 true
            EventBus.getDefault().unregister(this);//解除注册 EventBus
        }
        if (loading != null) {
            hideLoading();
            loading = null;
        }
    }

    /**
     * 绑定Presenter
     */
    public abstract P bindPresenter();

    public boolean useEventBus() {
        return false;
    }

    /**
     * 显示加载
     */
    public void showLoading() {
        if (loading == null) {
            loading = LoadingHelper.loading(context, false);
        }
        loading.show();
    }


    /**
     * 隐藏加载
     */
    public void hideLoading() {
        if (loading != null) {
            loading.dismiss();
        }
    }


    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 {@code null}
     */
    public void showMessage(@NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 跳转 {@link Activity}
     *
     * @param intent {@code intent} 不能为 {@code null}
     */
    public void launchActivity(@NonNull Intent intent) {
        startActivity(intent);
    }


    /**
     * 杀死自己
     */
    public void killMyself() {
        getActivity().finish();
    }
}
