package com.example.basemvplib.view.iview;

/**
 * @ClassName: IBaseMvpView
 * @author create by Tang
 * @date date 16/10/26 上午11:04
 * @Description: MVP模式中View层接口
 * Presenter层会调用下面三个方法通知View层处理事件
 */
public interface IBaseMvpView {

    /**
     * @Method: showProgress
     * @author create by Tang
     * @date date 16/10/26 上午11:06
     * @Description: 是否显示加载进度
     */
    void showProgress(final boolean show);

    /**
     * @Method: showNetworkError
     * @author create by Tang
     * @date date 16/10/26 上午11:08
     * @Description: 网络连接错误时Presenter层会调用该方法通知View层
     */
    void showNetworkError(int errorCode, String errorDesc, String ApiInterface);

    /**
     * @Method: showServerError
     * @author create by Tang
     * @date date 16/10/26 上午11:09
     * @Description: 服务器返回错误时Presenter层会调用该方法通知View层
     */
    void showServerError(int errorCode, String errorDesc);


}
