package com.mvp.framework.module.test.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.presenter.BasePaginationPresenter;
import com.mvp.framework.module.base.view.adapter.BaseListAdapter;
import com.mvp.framework.module.base.view.fragment.MvpListFragment;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.presenter.NuoMiCategoryPresenter;
import com.mvp.framework.module.test.view.adapter.NuoMiCategoryAdapter;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryMvpListView;

import java.util.List;

/**
 * @ClassName: NuomiCategoryFragment
 * @author create by Tang
 * @date date 16/10/28 上午11:20
 * @Description: TODO
 */

public class NuomiCategoryFragment extends MvpListFragment implements INuoMiCategoryMvpListView{
    @Override
    public RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(getActivity());
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
