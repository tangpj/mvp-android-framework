package com.mvp.framework.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
*@author By Tang
 * 请求队列
*Created on 2016-04-14 15:42
*/
public class SingletonRequestQueue {
    private static SingletonRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private SingletonRequestQueue(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonRequestQueue getInstance(Context context){
        if (mInstance == null){
            mInstance = new SingletonRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
