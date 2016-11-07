package com.example.test.view.iview;

import com.example.basemvplib.view.iview.IMvpView;
import com.example.test.bean.NuoMiShopInfoBean;

/**
 * @ClassName: INuoMiShopInfoView
 * @author create by Tang
 * @date date 16/10/14 下午2:15
 * @Description: 显示汇率转换数据
 */

public interface INuoMiShopInfoView extends IMvpView {

    void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo);
}
