package com.example.test.view.iview;


import com.example.basemvplib.view.iview.IMvpListView;
import com.example.test.bean.NuoMiCategoryBean;

import java.util.List;

/**
 * @ClassName: INuoMiCategoryListView
 * @author create by Tang
 * @date date 16/10/12 下午5:46
 * @Description: TODO
 */

public interface INuoMiCategoryListView extends IMvpListView {

    void showNuoMiCategoryView(List<NuoMiCategoryBean> nuoMiCategoryList);
}
