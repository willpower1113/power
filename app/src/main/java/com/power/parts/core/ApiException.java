package com.power.parts.core;

/**
 * Created by Administrator on 2018/11/6.
 */

public class ApiException extends Exception {
    public static ApiException handleException(Throwable e){
        return new ApiException();
    }
}
