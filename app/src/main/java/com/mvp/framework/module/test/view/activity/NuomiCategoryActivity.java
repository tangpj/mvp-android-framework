package com.mvp.framework.module.test.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.presenter.BasePaginationPresenter;
import com.mvp.framework.module.base.view.activity.BaseListActivity;
import com.mvp.framework.module.base.view.adapter.BaseListAdapter;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.presenter.NuoMiCategoryPresenter;
import com.mvp.framework.module.test.view.adapter.NuoMiCategoryAdapter;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryView;

import java.util.List;

/**
 * @ClassName: NuomiCategoryActivity
 * @author create by Tang
 * @date date 16/10/24 下午5:10
 * @Description: 测试糯米分类
 */
public class NuomiCategoryActivity extends BaseListActivity<NuoMiCategoryBean> implements INuoMiCategoryView{


    @Override
    public RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public BaseListAdapter setAdapter() {
        return new NuoMiCategoryAdapter();
    }

    @Override
    public BasePaginationPresenter setPresenter() {
        return new NuoMiCategoryPresenter(this);
    }

    @Override
    public BasePaginationParams setParams() {
        return null;
    }


    @Override
    public void showNuoMiCategoryView(List<NuoMiCategoryBean> nuoMiCategoryList) {
        setData(nuoMiCategoryList);
    }
}
