package com.example.basemvplib.model;

import android.text.TextUtils;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.baseapp.MyApp;
import com.example.basemvplib.config.ServerManager;
import com.example.basemvplib.model.imodel.IBaseModel;
import com.example.basemvplib.network.MyVolleyRequest;
import com.example.basemvplib.network.SingletonRequestQueue;
import com.example.basemvplib.presenter.ipresenter.IBasePresenter;

import org.json.JSONObject;

/**
 * @ClassName: VolleyModel
 * @author create by Tang
 * @date date 16/9/29 下午3:17
 * @Description: 通过volley框架从服务器获取数据
 */
public class VolleyModel implements IBaseModel {

    private static final int  VOLLEY_ERROR = 10001;   //网络错误
    private static final String VOLLEY_ERROR_DESC = "网络错误，请检查网络连接";

    private MyVolleyRequest request;

    private int method = Request.Method.GET;       //请求方式，默认get
    private String apiInterface;
    private String address;

    private IBasePresenter basePresenter;

    public VolleyModel(IBasePresenter basePresenter){
        this.basePresenter = basePresenter;
    }


    @Override
    public void sendRequestToServer() {
        String serverUrl;
        if (!TextUtils.isEmpty(address)){
            serverUrl = address + apiInterface;
        }else {
            serverUrl = ServerManager.getServerUrl(apiInterface);
        }
        request = new MyVolleyRequest(method, serverUrl
                , basePresenter.getParams()
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                basePresenter.accessSucceed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                basePresenter.volleyError(VOLLEY_ERROR
                        , VOLLEY_ERROR_DESC,apiInterface);
            }
        });
        SingletonRequestQueue.getInstance(MyApp.getInstance()).addRequestQueue(request);
    }

    @Override
    public void setMethod(int method ){
        this.method = method;
    }

    @Override
    public void setApiInterface(String apiInterface){
        this.apiInterface = apiInterface;
    }

    @Override
    public void setServerAddress(String address) {
        this.address = address;
    }

    @Override
    public void cancelRequest() {
        if (request != null && !request.isCanceled()){
            request.cancel();
        }

    }
}
