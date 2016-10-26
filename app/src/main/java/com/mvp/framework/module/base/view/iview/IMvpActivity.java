package com.mvp.framework.module.base.view.iview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName: IMvpActivity
 * @author create by Tang
 * @date date 16/10/20 上午11:19
 * @Description: 处理非列表型数据Activity接口
 */

public interface IMvpActivity extends IActivity{

    /**
     * @Method: onCreateView
     * @author create by Tang
     * @date date 16/10/20 下午2:32
     * @Description: 创建子activity布局
     */
    @NonNull
    View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState);

    /**
     * @Method: onReconnection
     * @author create by Tang
     * @date date 16/10/20 下午4:00
     * @Description: 重新连接
     * 这里要注意的是，如果一个页面里面有多个获取数据的presenter，
     * 需要确认获取数据失败的presenter
     * 具体需要根据实际业务来处理
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
