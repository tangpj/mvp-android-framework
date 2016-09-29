package com.mvp.framework.module.base.presenter.ipresenter;

import java.util.ArrayList;

/**
 * @ClassName: IBasePaginationPresenter
 * @author create by Tang
 * @date date 16/8/22 下午5:03
 * @Description: 列表型数据presenter基类
 */
public interface IBasePaginationPresenter<T,S> extends IBasePresenter<T> {

    void setData(ArrayList<S> list);

    ArrayList<S> getData();

    void refresh(T params);

    void currentRefresh();

    int getListSize();

    void setPaginationEnable(boolean enable);

}
