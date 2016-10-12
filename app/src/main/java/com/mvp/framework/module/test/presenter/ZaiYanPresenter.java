package com.mvp.framework.module.test.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.mvp.framework.config.ApiInterface;
import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.base.response.BaiduBaseResponse;
import com.mvp.framework.module.test.data.ZaiYanData;
import com.mvp.framework.module.test.view.iview.IZaiYanView;

/**
 * @ClassName: ZaiYanPresenter
 * @author create by Tang
 * @date date 16/9/29 下午5:00
 * @Description:
 * 测试API
 * 数据来源,百度宅言API
 */


public class ZaiYanPresenter extends BasePresenter<BaseParams,ZaiYanData>{

    private static final String TAG = "ZaiYanPresenter";

    private IZaiYanView testView;


    public ZaiYanPresenter(IZaiYanView testView) {
        super(testView);
        this.testView = testView;
        getModel().setApiInterface(ApiInterface.ZAI_YAN);

    }

    @Override
    public void serverResponse(BaiduBaseResponse<ZaiYanData> response) {
        Log.d(TAG, "serverResponse: " + response);
        if (TextUtils.isEmpty(response.errNum)){
            testView.showTestView();
        }else {
            testView.showServerError(response.errNum,response.errMsg);
        }
    }

}
