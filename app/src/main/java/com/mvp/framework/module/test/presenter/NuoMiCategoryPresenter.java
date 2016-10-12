package com.mvp.framework.module.test.presenter;

import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryView;

import java.util.List;

import static com.mvp.framework.config.ApiInterface.NUO_MI_CATEGOR;

/**
 * @ClassName: NuoMiCategoryPresenter
 * @author create by Tang
 * @date date 16/10/12 下午5:40
 * @Description: TODO
 */

public class NuoMiCategoryPresenter extends BasePresenter<BaseParams,List<NuoMiCategoryBean>>{

    private INuoMiCategoryView nuoMiCategoryView;

    public NuoMiCategoryPresenter(INuoMiCategoryView nuoMiCategoryView) {
        super(nuoMiCategoryView);
        this.nuoMiCategoryView = nuoMiCategoryView;
        getModel().setApiInterface(NUO_MI_CATEGOR);
    }

    @Override
    public void serverResponse(List<NuoMiCategoryBean> data) {
        nuoMiCategoryView.showNuoMiCategoryView(data);
    }
}
