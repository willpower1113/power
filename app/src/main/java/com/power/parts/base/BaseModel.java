package com.power.parts.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.power.parts.base.delegate.IModel;
import com.power.parts.base.integration.IRepositoryManager;


/**
 * Created by Administrator on 2018/11/5.
 *================================================
 * 基类 Model
 */

public abstract class BaseModel implements IModel, LifecycleObserver {

    /**
     * 在框架中 {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    @Override
    public void onDestroy() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }

}
