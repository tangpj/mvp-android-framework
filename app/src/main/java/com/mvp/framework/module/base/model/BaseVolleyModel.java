package com.mvp.framework.module.base.model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mvp.framework.MyApp;
import com.mvp.framework.config.ServerManager;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePresenter;
import com.mvp.framework.network.MyVolleyRequest;
import com.mvp.framework.network.SingletonRequestQueue;

import org.json.JSONObject;

/**
 * @ClassName: BaseVolleyModel
 * @author create by Tang
 * @date date 16/9/29 下午3:17
 * @Description: 通过volley框架从服务器获取数据
 */
public class BaseVolleyModel implements IBaseModel {

    private static final int  VOLLEY_ERROR = 10001;   //网络错误
    private static final String VOLLEY_ERROR_DESC = "点击屏幕重新加载";

    private MyVolleyRequest request;

    private int method = Request.Method.GET;       //请求方式，默认get
    private String apiInterface;

    private IBasePresenter basePresenter;

    public BaseVolleyModel(IBasePresenter basePresenter){
        this.basePresenter = basePresenter;
    }


    @Override
    public void sendRequestToServer() {

        request = new MyVolleyRequest(method, ServerManager.getServerUrl(apiInterface)
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
    public void cancelRequest() {
        if (request != null){
            request.cancel();
        }

    }
}
