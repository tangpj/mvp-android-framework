package com.mvp.framework.module.test.view.iview;

import com.mvp.framework.module.base.view.iview.IBasePaginationView;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;

import java.util.List;

/**
 * @ClassName: INuoMiCategoryView
 * @author create by Tang
 * @date date 16/10/12 下午5:46
 * @Description: TODO
 */

public interface INuoMiCategoryView extends IBasePaginationView {

    void showNuoMiCategoryView(List<NuoMiCategoryBean> nuoMiCategoryList);
}
