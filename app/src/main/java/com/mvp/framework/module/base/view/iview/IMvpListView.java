package com.mvp.framework.module.base.view.iview;

/**
 * @ClassName: IMvpListView
 * @author create by Tang
 * @date date 16/10/24 下午4:53
 * @Description: TODO
 */
public interface IMvpListView extends IBaseMvpView {

    /**
     * @Method: isNextPage
     * @author create by Tang
     * @date date 16/10/20 下午5:56
     * @Description: 设置列表数据
     * @param nextPage 是否有下一页，大于0为有
     */
    void isNextPage(int nextPage);

}
