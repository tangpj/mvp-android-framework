package com.example.basemvplib.presenter;

import com.example.basemvplib.model.BaseUploadModel;
import com.example.basemvplib.model.imodel.IBaseModel;
import com.example.basemvplib.params.FileUploadParams;
import com.example.basemvplib.presenter.ipresenter.IBaseUploadPresenter;
import com.example.basemvplib.view.iview.IBaseMvpView;

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

    private IBaseMvpView baseView;
    private IBaseModel uploadUploadModel;
    private List<FileUploadParams> params;

    protected BaseUploadPresenter(IBaseMvpView baseView){

        this.baseView = baseView;
        this.uploadUploadModel = new BaseUploadModel(this);
    }

    @Override
    public List<FileUploadParams> getParams(){
        return params;
    }

    @Override
    public void accessServer(List<FileUploadParams> params) {
        baseView.showProgress(true);
        this.params = params;
        uploadUploadModel.sendRequestToServer();
    }

    @Override
    public void accessSucceed(JSONObject response) {
        baseView.showProgress(false);
        serverResponse(String.valueOf(response));
    }


    @Override
    public void volleyError(int errorCode, String errorDesc, String ApiInterface) {
        baseView.showNetworkError(errorCode,errorDesc,ApiInterface);
    }

    @Override
    public void cancelRequest() {
        uploadUploadModel.cancelRequest();
    }
}
