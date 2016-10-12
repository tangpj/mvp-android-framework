package com.mvp.framework.module.base.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvp.framework.module.base.model.BaseVolleyModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePresenter;
import com.mvp.framework.module.base.response.BaiduBaseResponse;
import com.mvp.framework.module.base.view.IBaseView;
import com.mvp.framework.utils.JsonUtil;


import org.json.JSONObject;

import java.util.Map;

/**
 * @ClassName: BasePresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:14
 * @Description: 普通presenter基类
 * @P: 提交参数类
 * @D: 服务器返回数据
 */
public abstract class BasePresenter<P extends BaseParams,D>
        implements IBasePresenter<P> {

    public abstract void serverResponse(BaiduBaseResponse<D> response);

    private IBaseModel baseModel;
    private IBaseView baseView;
    private P params;

    protected BasePresenter(IBaseView baseView){
        this.baseView = baseView;
        this.baseModel = new BaseVolleyModel(this) ;
    }

    @Override
    public Map getParams(){
        if (params != null){
            return params.toMap();
        }else {
            return null;
        }
    }

    @Override
    public IBaseModel getModel(){
        return baseModel;
    }


    @Override
    public void accessServer(P params) {
        this.params = params;
        baseView.showProcess(true);
        baseModel.sendRequestToServer();
    }

    @Override
    public void accessSucceed(JSONObject response) {
        baseView.showProcess(false);
        BaiduBaseResponse<D> mResponse = JsonUtil.fromJson(response,BaiduBaseResponse.class);
        serverResponse(mResponse);
    }


    @Override
    public void volleyError(int errorCode, String errorDesc, String ApiInterface) {
        baseView.showVolleyError(errorCode,errorDesc,ApiInterface);
    }

    @Override
    public void cancelRequest() {
        baseModel.cancelRequest();
    }
}
