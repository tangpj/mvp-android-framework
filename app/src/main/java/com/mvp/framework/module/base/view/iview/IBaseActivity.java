package com.mvp.framework.module.base.view.iview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName: IBaseActivity
 * @author create by Tang
 * @date date 16/10/20 上午11:19
 * @Description: Activity的基本处理
 */

public interface IBaseActivity extends IBaseView{

    /**
     * @Method: initView
     * @author create by Tang
     * @date date 16/10/20 下午2:32
     * @Description: 创建子activity布局
     */
    @NonNull
    View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState);


    /**
     * @Method: Reconnection
     * @author create by Tang
     * @date date 16/10/20 下午4:00
     * @Description: 重新连接
     * 这里要注意的是，如果一个页面里面有多个获取数据的presenter，
     * 需要确认获取数据失败的presenter
     * 具体需要根据实际业务来处理
     */
    void reconnection();

    /**
     * @Method: setTitle
     * @author create by Tang
     * @date date 16/10/20 上午11:06
     * @Description: 设置页面标题
     */
    void setTitle(@NonNull String title);

    void setProgressType(int type);

    /**
     * @Method: setDisplayHomeAsUpEnabled
     * @author create by Tang
     * @date date 16/10/20 上午11:16
     * @Description: ActionBar上是否显示返回按钮
     */
    @NonNull
    boolean setDisplayHomeAsUpEnabled();

    FloatingActionButton getFloatingActionButton();
}
