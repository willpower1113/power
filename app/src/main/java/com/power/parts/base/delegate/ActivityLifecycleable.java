package com.power.parts.base.delegate;

import android.app.Activity;

import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by Administrator on 2018/11/5.
 * 让 {@link Activity} 实现此接口,即可正常使用 {@link RxLifecycle}
 */

public interface ActivityLifecycleable extends Lifecycleable<ActivityEvent> {
}
