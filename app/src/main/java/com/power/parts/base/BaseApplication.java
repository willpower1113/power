package com.power.parts.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/11/5.
 */

public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
