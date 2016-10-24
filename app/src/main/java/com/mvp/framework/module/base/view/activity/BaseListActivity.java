package com.mvp.framework.module.base.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.mvp.framework.R;
import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.presenter.BasePaginationPresenter;
import com.mvp.framework.module.base.view.adapter.BaseListAdapter;
import com.mvp.framework.module.base.view.iview.IBaseListActivity;
import com.mvp.framework.module.base.view.iview.IBasePaginationView;

import java.util.List;

/**
 * @ClassName: BaseListActivity
 * @author create by Tang
 * @date date 16/10/24 下午1:55
 * @Description:
 * 列表型Activity基类
 * 列表型的Activity可继承该基类
 */
public abstract class BaseListActivity<Bean> extends AppCompatActivity
        implements IBaseListActivity<Bean>,IBasePaginationView {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBar ab;

    private RecyclerView baseRecyclerView;
    private SwipeRefreshLayout baseRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    private BaseListAdapter adapter;
    private int lastVisibleItem;
    private int[] lastPositions;

    //如果有下一页nextPage大于0
    private int nextPage;

    private BasePaginationPresenter presenter;

    //需要替换的layoutId
    private int mLayoutId = 0;

    /**
     * @Method: findMax
     * @author create by Tang
     * @date date 16/10/24 下午3:48
     * @Description: 用于查找int数组中最大的数
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public void setMContentView(int layoutId){
        this.mLayoutId = layoutId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        presenter = setPresenter();
        initBaseView();

        //自动刷新
        baseRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        onRefresh();

    }

    private void initBaseView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        baseRecyclerView = (RecyclerView) findViewById(R.id.base_recycler_view) ;
        baseRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_refresh_layout);

        setSupportActionBar(toolbar);
        ab = getSupportActionBar();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        layoutManager = setLayoutManager();
        adapter = setAdapter();
        baseRecyclerView.setLayoutManager(layoutManager);
        baseRecyclerView.setAdapter(adapter);
        baseRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (nextPage > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    onLoadingNextPage();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager instanceof LinearLayoutManager){
                    lastVisibleItem
                            = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if (layoutManager instanceof GridLayoutManager){
                    lastVisibleItem = ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if (layoutManager instanceof StaggeredGridLayoutManager){
                    StaggeredGridLayoutManager staggeredGridLayoutManager
                            = (StaggeredGridLayoutManager) layoutManager;
                    if (lastPositions == null){
                        lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                        staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                        lastVisibleItem = findMax(lastPositions);
                    }
                }
            }
        });

        baseRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        baseRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseListActivity.this.onRefresh();
            }
        });


    }

    @Override
    public void onRefresh() {
        presenter.refresh(setParams());
    }

    @Override
    public void onLoadingNextPage() {
        presenter.loading();
    }

    @Override
    public void onRefreshIndexPage(int index) {
        presenter.refreshIndexPage(index);
    }

    @Override
    public void setData(List<Bean> data) {
        adapter.setData(data);
    }

    @Override
    public void isNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public void showProgress(boolean show) {
        baseRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showNetworkError(int errorCode, String errorDesc, String ApiInterface) {

    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {

    }

    public abstract RecyclerView.LayoutManager setLayoutManager();

    public abstract BaseListAdapter setAdapter();

    public abstract BasePaginationPresenter setPresenter();

    public abstract BasePaginationParams setParams();

}
