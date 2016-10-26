package com.mvp.framework.module.base.view.iview;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

/**
 * @ClassName: IActivity
 * @author create by Tang
 * @date date 16/10/26 上午10:59
 * @Description: Activity基础接口
 */

public interface IActivity {

    /**
     * @Method: setTitle
     * @author create by Tang
     * @date date 16/10/20 上午11:06
     * @Description: 设置页面标题
     */
    void setTitle(String title);

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
