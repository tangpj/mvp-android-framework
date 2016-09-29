package com.mvp.framework.module.base.view;

/**
*@author By Tang
*created at 16/4/22  下午8:25
*/
public interface IBaseView {

    void showProcess(final boolean show);

    void showVolleyError(int errorCode, String errorDesc, String ApiInterface);

    void showServerError(String errorCode, String errorDesc);
}
