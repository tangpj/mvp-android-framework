package com.example.basemvplib.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyVolleyRequest extends Request<JSONObject> {
    private Listener<JSONObject> listener;
    private Map<String, String> params;

    private static final int DEFAULT_TIMEOUT_MS = 1500;   //网络超时时间

    public MyVolleyRequest(String url, Map<String, String> params,
                           Listener<JSONObject> responseListener, ErrorListener errorListener) {//GET请求
        super(Method.GET, url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }


    public MyVolleyRequest(int method, String url, Map<String, String> params,
                           Listener<JSONObject> responseListener, ErrorListener errorListener) {//GET和POST
        super(method, url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams()
            throws AuthFailureError {
        return params;
    }



    /**
     *设置头部信息(cookie,token等)
     *Created on 2016-04-14 18:48
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> header = new HashMap<>();
        //设置百度api store请求头
        header.put("apikey","");
        return header;
    }


    @Override
    public RetryPolicy getRetryPolicy() {
        new DefaultRetryPolicy(
                DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return super.getRetryPolicy();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub  
        listener.onResponse(response);
    }
}  