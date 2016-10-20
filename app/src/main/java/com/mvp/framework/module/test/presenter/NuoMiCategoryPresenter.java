package com.mvp.framework.module.test.presenter;

import android.util.Log;

import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.BasePaginationPresenter;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryView;

import java.util.ArrayList;
import java.util.List;

import static com.mvp.framework.config.ApiInterface.NUO_MI_CATEGOR;

/**
 * @ClassName: NuoMiCategoryPresenter
 * @author create by Tang
 * @date date 16/10/12 下午5:40
 * @Description: 暂未完成，请勿查看
 */

public class NuoMiCategoryPresenter extends BasePaginationPresenter<BasePaginationParams,NuoMiCategoryBean> {

    private INuoMiCategoryView nuoMiCategoryView;

    public NuoMiCategoryPresenter(INuoMiCategoryView nuoMiCategoryView) {
        super(nuoMiCategoryView,NuoMiCategoryBean.class);
        this.nuoMiCategoryView = nuoMiCategoryView;
        getModel().setApiInterface(NUO_MI_CATEGOR);
    }


    @Override
    public void serverResponse(List<NuoMiCategoryBean> list) {
        nuoMiCategoryView.showNuoMiCategoryView(list);
    }
}
