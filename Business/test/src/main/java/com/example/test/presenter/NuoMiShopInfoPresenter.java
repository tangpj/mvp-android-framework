package com.example.test.presenter;

import com.android.volley.Request;
import com.example.basemvplib.presenter.BasePresenter;
import com.example.test.config.ApiInterface;
import com.example.test.bean.NuoMiShopInfoBean;
import com.example.test.params.NuoMiShopInfoParams;
import com.example.test.view.iview.INuoMiShopInfoView;


/**
 * @ClassName: NuoMiShopInfoPresenter
 * @author create by Tang
 * @date date 16/10/14 下午2:14
 * @Description: 根据id查询商家
 */

public class NuoMiShopInfoPresenter extends BasePresenter<NuoMiShopInfoParams,NuoMiShopInfoBean> {

    private INuoMiShopInfoView currencyView;

    public NuoMiShopInfoPresenter(INuoMiShopInfoView currencyView) {
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
