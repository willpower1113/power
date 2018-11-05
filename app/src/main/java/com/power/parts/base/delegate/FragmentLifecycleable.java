package com.power.parts.base.delegate;

import android.support.v4.app.Fragment;

import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by Administrator on 2018/11/5.
 * 让 {@link Fragment} 实现此接口,即可正常使用 {@link RxLifecycle}
 */

public interface FragmentLifecycleable extends Lifecycleable<FragmentEvent> {
}
