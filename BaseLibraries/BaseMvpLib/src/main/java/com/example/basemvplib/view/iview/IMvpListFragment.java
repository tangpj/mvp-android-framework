package com.example.basemvplib.view.iview;

import android.view.View;

import java.util.List;

/**
 * @ClassName: IMvpListFragment
 * @author create by Tang
 * @date date 16/10/28 上午9:45
 * @Description: 处理列表类型Fragment接口
 * 方法与IMvpListActivity类似
 * 但是不继承IActivity接口
 */

public interface IMvpListFragment<Bean> {

    /**
     * @ClassName: IMvpListFragment
     * @author create by Tang
     * @date date 16/10/28 上午9:59
     * @Description: 可在在这里做一些初始化操作
     */
    void onCreate(View view);

    /**
     * @ClassName: IMvpListFragment
     * @author create by Tang
     * @date date 16/10/28 上午9:59
     * @Description: 刷新列表
     */
    void onRefresh();

    /**
     * @Method: onLoadingNextPage
     * @author create by Tang
     * @date date 16/10/28 上午9:59
     * @Description: 加载下一页
     */
    void onLoadingNextPage();


    /**
     * @Method: onRefreshIndexPage
     * @author create by Tang
     * @date date 16/10/28 上午9:59
     * @Description: 刷新index所在的页面
     */
    void onRefreshIndexPage(int index);


   /**
    * @Method: setData
    * @author create by Tang
    * @date date 16/10/28 上午9:58
    * @Description: 设置数据
    */
    void setData(List<Bean> data);

    /**
     * @Method: reSetContentView
     * @author create by Tang
     * @date date 16/10/28 上午10:46
     * @Description: 重新设置ContentView,新设置的ContentView中的id必须要和activity_base_list中的一样
     */
    void reSetContentView(int layoutId);
}
