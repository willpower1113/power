package com.power.parts.mvp.presenter;

import com.power.parts.base.BasePresenter;
import com.power.parts.base.BaseSubscriber;
import com.power.parts.core.ApiException;
import com.power.parts.core.ApiUtils;
import com.power.parts.mvp.model.LoginModel;
import com.power.parts.mvp.view.LoginView;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/11/5.
 */

public class LoginPresenter extends BasePresenter<LoginModel, LoginView> {

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param rootView
     * @param mModel
     */
    public LoginPresenter(LoginView rootView, LoginModel mModel) {
        super(rootView, mModel);
    }

    /**
     * 登录
     */
    public void login(Map<String, String> params) {
        BaseSubscriber disposable = new BaseSubscriber() {
            @Override
            public void onHttpError(ApiException e) {

            }
        };
        addDispose(disposable);
        ApiUtils.getInstance().post().login(null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposable);
    }

}
