package com.power.parts.mvp.presenter;

import com.power.parts.base.BasePresenter;
import com.power.parts.mvp.model.HomePageModel;
import com.power.parts.mvp.view.HomePageView;

/**
 * Created by Administrator on 2018/11/5.
 */

public class HomePagePresenter extends BasePresenter<HomePageModel, HomePageView> {

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param rootView
     * @param mModel
     */
    public HomePagePresenter(HomePageView rootView, HomePageModel mModel) {
        super(rootView, mModel);
    }
}
