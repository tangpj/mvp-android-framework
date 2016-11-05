package com.example.test.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.utillib.LogUtil;
import com.mvp.framework.module.params.BasePaginationParams;
import com.mvp.framework.module.presenter.BasePaginationPresenter;
import com.mvp.framework.module.view.adapter.BaseListAdapter;
import com.mvp.framework.module.view.fragment.MvpListFragment;
import com.example.test.bean.NuoMiCategoryBean;
import com.example.test.presenter.NuoMiCategoryPresenter;
import com.example.test.view.adapter.NuoMiCategoryAdapter;
import com.example.test.view.iview.INuoMiCategoryListView;

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
