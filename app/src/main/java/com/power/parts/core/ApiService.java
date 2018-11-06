package com.power.parts.core;


import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public class ApiService {

    public static final String HOST = "";


    public interface ApiPost {

        @POST("")
        Observable<JsonObject> login(@QueryMap Map<String, String> options);
    }

    public interface ApiGet{

    }
}
