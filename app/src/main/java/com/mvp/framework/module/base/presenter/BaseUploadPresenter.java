package com.mvp.framework.module.base.presenter;

import com.mvp.framework.module.base.model.BaseUploadModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.FileUploadParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBaseUploadPresenter;
import com.mvp.framework.module.base.view.iview.IBaseView;


import org.json.JSONObject;

import java.util.List;

/**
 * @ClassName: BaseUploadPresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:13
 * @Description: 上传文件
 */
public abstract class BaseUploadPresenter implements IBaseUploadPresenter {

    public abstract void serverResponse(String response) ;

    private IBaseView baseView;
    private IBaseModel uploadUploadModel;
    private List<FileUploadParams> params;

    protected BaseUploadPresenter(IBaseView baseView){

        this.baseView = baseView;
        this.uploadUploadModel = new BaseUploadModel(this);
    }

    @Override
    public List<FileUploadParams> getParams(){
        return params;
    }

    @Override
    public void accessServer(List<FileUploadParams> params) {
        baseView.showProcess(true);
        this.params = params;
        uploadUploadModel.sendRequestToServer();
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
        uploadUploadModel.cancelRequest();
    }
}
