package com.mvp.framework.module.base.view.iview;

/**
*@author By Tang
*created at 16/4/22  下午8:25
*/
public interface IBaseView {

    //网络回调的基本事件处理
    void showProcess(final boolean show);

    void showVolleyError(int errorCode, String errorDesc, String ApiInterface);

    void showServerError(int errorCode, String errorDesc);

}
