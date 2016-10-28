package com.mvp.framework.module.test.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.fragment.MvpFragment;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.presenter.NuoMiShopInfoPresenter;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoView;

/**
 * @ClassName: NuoMiShopInfoFragment
 * @author create by Tang
 * @date date 16/10/28 下午2:14
 * @Description: TODO
 */
public class NuoMiShopInfoFragment extends MvpFragment implements INuoMiShopInfoView {

    private TextView shopInfo;

    private NuoMiShopInfoPresenter presenter;

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuomi_shop_info,container,false);
        setProgressType(PROGRESS_TYPE_DROP_DOWN);
        shopInfo = (TextView) view.findViewById(R.id.shop_info);
        presenter = new NuoMiShopInfoPresenter(this);
        presenter.accessServer(null);
        return view;
    }

    @Override
    public void onReconnection() {
        super.onReconnection();
        presenter.accessServer(null);
    }

    @Override
    public void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo) {
        shopInfo.setText("商户名称：" + nuoMiShopInfo.shopName
                + "\n商户地址： " + nuoMiShopInfo.address
                + "\n营业时间： " + nuoMiShopInfo.open_time);
    }
}
