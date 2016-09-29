package com.mvp.framework.module.test.presenter;

import com.google.gson.Gson;
import com.mvp.framework.config.ApiInterface;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.base.response.BaseResponse;
import com.mvp.framework.module.base.view.IBaseView;
import com.mvp.framework.module.test.view.iview.ITestView;

/**
 * @ClassName: TestPresenter
 * @author create by Tang
 * @date date 16/9/29 下午5:00
 * @Description: TODO
 */

public class TestPresenter extends BasePresenter{

    private ITestView testView;

    public TestPresenter(ITestView testView) {
        super(testView);
        this.testView = testView;
        getModel().setApiInterface(ApiInterface.TEST_SUCCESS);

    }

    @Override
    public void serverResponse(String response) {
        BaseResponse baseResponse = new Gson().fromJson(response,BaseResponse.class);
        if (baseResponse.status.success){
            testView.showTestView();
        }else {
            testView.showServerError(baseResponse.status.errorCode,baseResponse.status.errorDesc);
        }
    }

}
