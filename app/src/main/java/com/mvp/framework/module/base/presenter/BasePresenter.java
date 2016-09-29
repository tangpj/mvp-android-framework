package com.mvp.framework.module.base.presenter;

import com.mvp.framework.module.base.model.BaseVolleyModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePresenter;
import com.mvp.framework.module.base.view.IBaseView;


import org.json.JSONObject;

import java.util.Map;

/**
 * @ClassName: BasePresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:14
 * @Description: 普通presenter基类
 */
public abstract class BasePresenter<T extends BaseParams> implements IBasePresenter<T> {

    public abstract void serverResponse(String response);

    private IBaseModel baseModel;
    private IBaseView baseView;
    private T params;

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
    public void accessServer(T params) {
        this.params = params;
        baseView.showProcess(true);
        baseModel.sendRequestToServer();
    }

    @Override
    public void accessSucceed(JSONObject response) {
        baseView.showProcess(false);
        serverResponse(String.valueOf(response));
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
