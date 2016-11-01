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
import com.mvp.framework.module.test.view.iview.INuoMiCategoryListView;
import com.mvp.framework.utils.LogUtil;

import java.util.List;

/**
 * @ClassName: NuomiCategoryFragment
 * @author create by Tang
 * @date date 16/10/28 上午11:20
 * @Description: TODO
 */

public class NuomiCategoryFragment extends MvpListFragment implements INuoMiCategoryListView {

    private NuoMiCategoryAdapter adapter;
    private NuoMiCategoryPresenter presenter;

    @Override
    public RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public BaseListAdapter setAdapter() {
        adapter = new NuoMiCategoryAdapter();
        adapter.setOnItemClickListener(new BaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                //测试刷新指定页面
                LogUtil.d(NuomiCategoryFragment.this.getClass(),position + "");
                presenter.refreshIndexPage(position);
            }
        });
        return adapter;
    }

    @Override
    public BasePaginationPresenter setPresenter() {
        presenter = new NuoMiCategoryPresenter(this);
        return presenter;
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
