package com.example.test.presenter;

import com.mvp.framework.module.params.BasePaginationParams;
import com.mvp.framework.module.presenter.BasePaginationPresenter;
import com.example.test.bean.NuoMiCategoryBean;
import com.example.test.view.iview.INuoMiCategoryListView;

import java.util.List;

import static com.mvp.framework.config.ApiInterface.NUO_MI_CATEGOR;

/**
 * @ClassName: NuoMiCategoryPresenter
 * @author create by Tang
 * @date date 16/10/12 下午5:40
 * @Description: 暂未完成，请勿查看
 */

public class NuoMiCategoryPresenter extends BasePaginationPresenter<BasePaginationParams,NuoMiCategoryBean> {

    private INuoMiCategoryListView nuoMiCategoryView;

    public NuoMiCategoryPresenter(INuoMiCategoryListView nuoMiCategoryView) {
        super(nuoMiCategoryView,NuoMiCategoryBean.class);
        this.nuoMiCategoryView = nuoMiCategoryView;
        getModel().setApiInterface(NUO_MI_CATEGOR);
        setCount(7);
    }


    @Override
    public void serverResponse(List<NuoMiCategoryBean> list) {
        nuoMiCategoryView.showNuoMiCategoryView(list);
    }
}
