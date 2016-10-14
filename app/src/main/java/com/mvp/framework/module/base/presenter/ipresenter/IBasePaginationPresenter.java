package com.mvp.framework.module.base.presenter.ipresenter;

import java.util.ArrayList;

/**
 * @ClassName: IBasePaginationPresenter
 * @author create by Tang
 * @date date 16/8/22 下午5:03
 * @Description: 列表型数据presenter基类
 * @P: 提交到服务器的参数类
 * @
 */
public interface IBasePaginationPresenter<P,S> extends IBasePresenter<P> {


    //把服务器返回的数据添加到数据列表中
    void addData(ArrayList<S> list);

    //获取当前的数据
    ArrayList<S> getData();

    //刷新全部数据
    void refresh(P params);

    //刷新当前页数据
    void currentRefresh();

    //已获取的数据长度
    int getListSize();

    //是否启用分页功能
    void setPaginationEnable(boolean enable);

}
