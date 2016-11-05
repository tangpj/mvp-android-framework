package com.mvp.framework.module.test.view.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.mvp.framework.R;
import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.presenter.BasePaginationPresenter;
import com.mvp.framework.module.base.view.activity.MvpListActivity;
import com.mvp.framework.module.base.view.adapter.BaseListAdapter;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.presenter.NuoMiCategoryPresenter;
import com.mvp.framework.module.test.view.adapter.NuoMiCategoryAdapter;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryListView;

import java.util.List;

/**
 * @ClassName: NuomiCategoryActivityActivity
 * @author create by Tang
 * @date date 16/10/24 下午5:10
 * @Description: 测试糯米分类
 */
public class NuomiCategoryActivityActivity extends MvpListActivity<NuoMiCategoryBean> implements INuoMiCategoryListView {

    private NuoMiCategoryAdapter adapter;

    @Override
    public void onCreate() {
        super.onCreate();
        setTitle("糯米分类");
    }

    @Override
    public RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public BaseListAdapter setAdapter() {
        adapter = new NuoMiCategoryAdapter();
        adapter.setHeaderView(R.layout.test_header);
        adapter.setFooterView(R.layout.test_footer);
        return adapter;
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

    @Override
    public String setErrorString() {
        return "你的信号被狗吃了";
    }

    @Override
    public int setErrorImageResource() {
        return R.mipmap.my_error;
    }
}
