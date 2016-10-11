package com.mvp.framework.module.test.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
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
 * @Description:
 * 测试API
 * 数据来源,百度宅言API
 */


public class TestPresenter extends BasePresenter{

    private static final String TAG = "TestPresenter";

    private ITestView testView;


    public TestPresenter(ITestView testView) {
        super(testView);
        this.testView = testView;
        getModel().setApiInterface(ApiInterface.TEST);
        getModel().setMethod(Request.Method.GET);

    }

    @Override
    public void serverResponse(String response) {
        Log.d(TAG, "serverResponse: " + response);
        BaseResponse baseResponse = new Gson().fromJson(response,BaseResponse.class);
        if (TextUtils.isEmpty(baseResponse.errNum)){
            testView.showTestView();
        }else {
            testView.showServerError(baseResponse.errNum,baseResponse.errMsg);
        }
    }

}
