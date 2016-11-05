package com.mvp.framework.module.presenter.ipresenter;


import com.mvp.framework.module.params.FileUploadParams;

import org.json.JSONObject;

import java.util.List;

/**
 * @ClassName: IBaseUploadPresenter
 * @author create by Tang
 * @date date 16/8/22 下午5:01
 * @Description: 上传presenter基类接口
 */
public interface IBaseUploadPresenter {

    void accessServer(List<FileUploadParams> params);

    void accessSucceed(JSONObject response);

    List<FileUploadParams> getParams();

    void cancelRequest();

    void volleyError(int errorCode, String errorDesc, String ApiInterface);
}
