package com.power.parts.core;

import com.google.gson.GsonBuilder;
import com.power.parts.core.gson.BooleanDefaultFalseAdapter;
import com.power.parts.core.gson.DoubleDefault0Adapter;
import com.power.parts.core.gson.IntegerDefault0Adapter;
import com.power.parts.core.gson.LongDefault0Adapter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2018/11/5.
 */

public class ApiUtils {

    static ApiUtils utils;

    private Retrofit.Builder mBuilder;

    private ApiUtils() {
        //配置统一头部
        //配置OkHttpClient
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)  //超时时间
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)    //读取时间
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)   //写入时间
                .addInterceptor(new LoggingInterceptor())//添加日志拦截器
                .build();
        mBuilder = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)   //baseUrl
                .addConverterFactory(IGsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                        .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                        .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                        .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                        .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                        .registerTypeAdapter(long.class, new LongDefault0Adapter())
                        .registerTypeAdapter(Boolean.class, new BooleanDefaultFalseAdapter())
                        .registerTypeAdapter(boolean.class, new BooleanDefaultFalseAdapter())
                        .serializeNulls().setLenient().create()))//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加RxJava转换器
                .client(mOkHttpClient);   //配置OkHttpClient
    }

    public static ApiUtils getInstance() {
        if (utils == null) {
            synchronized (ApiUtils.class){
                if (utils == null){
                    utils = new ApiUtils();
                }
            }
        }
        return utils;
    }

    public Retrofit.Builder getRetrofit() {
        return mBuilder;
    }

    public ApiService.ApiPost post() {
        return mBuilder.build().create(ApiService.ApiPost.class);
    }

    public ApiService.ApiGet get() {
        return mBuilder.build().create(ApiService.ApiGet.class);
    }
}
