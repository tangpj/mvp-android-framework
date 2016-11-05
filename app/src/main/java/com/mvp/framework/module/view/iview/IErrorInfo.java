package com.mvp.framework.module.view.iview;

/**
 * @ClassName: IErrorInfo
 * @author create by Tang
 * @date date 16/10/26 上午11:11
 * @Description: 重设错误显示View
 */

public interface IErrorInfo {

    /**
     * @Method: setErrorImageResource
     * @author create by Tang
     * @date date 16/10/26 上午10:31
     * @Description: 设置错误显示图片
     */
    int setErrorImageResource();

    /**
     * @Method: setErrorString
     * @author create by Tang
     * @date date 16/10/26 上午10:31
     * @Description: 设置错误显示信息
     */
    String setErrorString();


    /**
     * @Method: onError
     * @author create by Tang
     * @date date 16/10/26 下午2:52
     * @Description: 错误处理
     */
    void onError(int errorCode,String errorDesc);
}
