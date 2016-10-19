package com.mvp.framework.module.base.presenter.ipresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IBasePaginationPresenter
 * @author create by Tang
 * @date date 16/8/22 下午5:03
 * @Description: 列表型数据presenter基类
 * @Params: 提交到服务器的参数类
 * @Bean: 队列数据项实体类
 */
public interface IBasePaginationPresenter<Params,Bean> extends IBasePresenter<Params> {


    //把服务器返回的数据添加到数据列表中
    void addData(ArrayList<Bean> list);

    //获取当前的数据
    List<Bean> getData();

    //刷新全部数据
    void refresh(Params params);

    //获取下一页数据
    void getNextPage();

    /**
     * @Method: refreshAssignPage
     * @author create by Tang
     * @date date 16/10/19 上午11:07
     * @Description: 刷新指定页
     * 只能对已经加载的数据执行此操作
     * @param page 需要刷新的页面
     */
    void refreshAssignPage(int page);

    //设置一次取数据数量
    void setCount(int count);

    void accessServer();

}
