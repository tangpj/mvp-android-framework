package com.mvp.framework.module.test.presenter;

import com.android.volley.Request;
import com.mvp.framework.config.ApiInterface;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.params.NuoMiShopInfoParams;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoMvpView;


/**
 * @ClassName: NuoMiShopInfoPresenter
 * @author create by Tang
 * @date date 16/10/14 下午2:14
 * @Description: 根据id查询商家
 */

public class NuoMiShopInfoPresenter extends BasePresenter<NuoMiShopInfoParams,NuoMiShopInfoBean>{

    private INuoMiShopInfoMvpView currencyView;

    public NuoMiShopInfoPresenter(INuoMiShopInfoMvpView currencyView) {
        super(currencyView,NuoMiShopInfoBean.class);
        this.currencyView = currencyView;
        getModel().setApiInterface(ApiInterface.NUO_MI_SHOP_INFO);
        getModel().setMethod(Request.Method.POST);
    }

    @Override
    public void serverResponse(NuoMiShopInfoBean data) {
        currencyView.showNuoMiShopInfoView(data);
    }

}
