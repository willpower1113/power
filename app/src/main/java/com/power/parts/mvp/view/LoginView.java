package com.power.parts.mvp.view;

import com.power.parts.base.delegate.IView;

/**
 * Created by Administrator on 2018/11/5.
 */

public interface LoginView extends IView {

    void onLoginSuccess();

    void onLoginFail();
}
