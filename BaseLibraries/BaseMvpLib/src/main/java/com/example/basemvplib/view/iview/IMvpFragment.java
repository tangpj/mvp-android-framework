package com.example.basemvplib.view.iview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName: IMvpFragment
 * @author create by Tang
 * @date date 16/10/26 上午10:49
 * @Description:
 * BaseActivity中实现该接口，
 * 主要作用是处理
 */

public interface IMvpFragment {

    /**
     * @Method:
     * @author create by Tang
     * @date date 16/10/27 上午10:38
     * @Description: 创建布局
     */
    View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * @Method: onReconnection
     * @author create by Tang
     * @date date 16/10/27 上午10:38
     * @Description: 重连
     */
    void onReconnection();

    /**
     * @Method: setProgressType
     * @author create by Tang
     * @date date 16/10/25 上午10:54
     * @Description:
     * 设置加载进度的样式
     * 初始化为默认模式，
     * 在子类中调用该方法可以重设进度条的样式
     */
    void setProgressType(int progressType);
}
