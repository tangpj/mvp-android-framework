package com.mvp.framework.module.base.view.iview;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

import java.util.List;

/**
 * @ClassName: IMvpListActivity
 * @author create by Tang
 * @date date 16/10/20 下午5:47
 * @Description: 列表型Activity接口
 * @Bean: 列表数据项实体
 */

public interface IMvpListActivity<Bean> extends IActivity{


    /**
     * @Method: onCreate
     * @author create by Tang
     * @date date 16/10/25 下午4:36
     * @Description: 可在在这里做一些初始化操作
     */
    void onCreate();

    /**
     * @Method: refresh
     * @author create by Tang
     * @date date 16/10/20 下午5:51
     * @Description: 刷新列表
     */
    void onRefresh();

    /**
     * @Method: 加载下一页
     * @author create by Tang
     * @date date 16/10/20 下午5:52
     * @Description: TODO
     */
    void onLoadingNextPage();


    /**
     * @Method: onRefreshIndexPage
     * @author create by Tang
     * @date date 16/10/20 下午5:53
     * @Description: 刷新index所在的页面
     */
    void onRefreshIndexPage(int index);


    /**
     * @Method: setData
     * @author create by Tang
     * @date date 16/10/24 下午6:09
     * @Description: 设置数据
     */
    void setData(List<Bean> data);

}
