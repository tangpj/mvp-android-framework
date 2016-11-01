package com.mvp.framework.module.test.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.activity.MvpActivity;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.presenter.NuoMiShopInfoPresenter;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoView;

/**
 * @ClassName: NuoMiShopInfoFragment
 * @author create by Tang
 * @date date 16/10/26 下午5:02
 * @Description: TODO
 */

public class NuoMiShopInfoActivity extends MvpActivity implements INuoMiShopInfoView {

    private TextView shopInfo;

    private NuoMiShopInfoPresenter nuoMiShopInfoPresenter;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_nuomi_shop_info,container,false);
        shopInfo = (TextView)view.findViewById(R.id.shop_info);
        nuoMiShopInfoPresenter = new NuoMiShopInfoPresenter(this);
        nuoMiShopInfoPresenter.accessServer(null);
        return view;
    }


    @Override
    public void onReconnection() {
        super.onReconnection();
        nuoMiShopInfoPresenter.accessServer(null);
    }

    @Override
    public void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo) {
        shopInfo.setText("商户名称：" + nuoMiShopInfo.shopName
                + "\n商户地址： " + nuoMiShopInfo.address
                + "\n营业时间： " + nuoMiShopInfo.open_time);
    }
}
