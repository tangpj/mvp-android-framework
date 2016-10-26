package com.mvp.framework.module.test.view.iview;

import com.mvp.framework.module.base.view.iview.IMvpView;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;

/**
 * @ClassName: INuoMiShopInfoMvpView
 * @author create by Tang
 * @date date 16/10/14 下午2:15
 * @Description: 显示汇率转换数据
 */

public interface INuoMiShopInfoMvpView extends IMvpView {

    void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo);
}
