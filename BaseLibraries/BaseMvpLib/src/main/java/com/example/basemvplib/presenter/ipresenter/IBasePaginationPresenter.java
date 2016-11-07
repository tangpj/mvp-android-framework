package com.example.basemvplib.presenter.ipresenter;

/**
 * @ClassName: IBasePaginationPresenter
 * @author create by Tang
 * @date date 16/8/22 下午5:03
 * @Description: 列表型数据presenter基类
 * @Params: 提交到服务器的参数类
 * @Bean: 队列数据项实体类
 */
public interface IBasePaginationPresenter<Params,Bean> extends IBasePresenter<Params> {

    //刷新全部数据
    void refresh(Params params);

    //加载下一页数据
    void loading();

    /**
     * @Method: refreshAssignPage
     * @author create by Tang
     * @date date 16/10/19 上午11:07
     * @Description: 刷新index所在页页面
     * @param index 待刷新数据的位置
     */
    void refreshIndexPage(int index);

    //设置一次取数据数量
    void setCount(int count);

    void accessServer();

}
