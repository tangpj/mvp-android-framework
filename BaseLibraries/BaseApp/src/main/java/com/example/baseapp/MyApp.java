package com.example.baseapp;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;


public class MyApp extends Application {


    private static final String TAG = "MedicalAPP";

    public static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        Fresco.initialize(this);
        super.onCreate();
        instance = this;
        initConfig();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    void initConfig(){
        // TODO: 16/9/29 初始化操作
    }

    
}
