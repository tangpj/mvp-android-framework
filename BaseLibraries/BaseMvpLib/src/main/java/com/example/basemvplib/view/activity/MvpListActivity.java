package com.example.basemvplib.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.basemvplib.R;
import com.example.basemvplib.params.BasePaginationParams;
import com.example.basemvplib.presenter.BasePaginationPresenter;
import com.example.basemvplib.view.adapter.BaseListAdapter;
import com.example.basemvplib.view.iview.IErrorInfo;
import com.example.basemvplib.view.iview.IMvpListActivity;
import com.example.basemvplib.view.iview.IMvpListView;
import com.example.utillib.LogUtil;

import java.util.List;

/**
 * @ClassName: MvpListActivity
 * @author create by Tang
 * @date date 16/10/24 下午1:55
 * @Description:
 * 列表型Activity基类
 * 获取列表型数据的Activity继承该类可以实现自动处理大部分数据
 */
public abstract class MvpListActivity<Bean> extends AppCompatActivity
        implements IMvpListActivity<Bean>,IMvpListView,IErrorInfo {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBar ab;

    private RecyclerView baseRecyclerView;
    private SwipeRefreshLayout baseRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    private BaseListAdapter adapter;
    private int lastVisibleItem;
    private int[] lastPositions;


    //错误显示View
    private LinearLayout errorLayout;
    private ImageView errorImage;
    private TextView errorText;

    //如果有下一页nextPage大于0
    private int nextPage;

    private BasePaginationPresenter presenter;

    //需要替换的layoutId
    private int mLayoutId = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mLayoutId != 0){
            setContentView(mLayoutId);
        }else {
            setContentView(R.layout.activity_mvp_list);
        }
        presenter = setPresenter();
        onCreate();

        //自动刷新
        baseRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        onRefresh();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreate() {
        initBaseView();
        // 子类需要初始化的话需要覆盖此类
    }

    @Override
    public void onRefresh() {
        if (presenter != null){
            presenter.refresh(setParams());
        }
    }

    @Override
    public void onLoadingNextPage() {
        if (presenter != null){
            presenter.loading();
        }
    }

    @Override
    public void onRefreshIndexPage(int index) {
        if (presenter != null) {
            presenter.refreshIndexPage(index);
        }
    }

    @Override
    public void setData(List<Bean> data) {
        if (errorLayout.getVisibility() == View.VISIBLE){
            errorLayout.setVisibility(View.GONE);
        }
        adapter.setData(data);
    }

    @Override
    public void reSetContentView(int layoutId){
        this.mLayoutId = layoutId;
    }

    @Override
    public void setTitle(String title) {
        ab.setTitle(title);
    }

    @NonNull
    @Override
    public boolean setDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }

    @Override
    public int setErrorImageResource() {
        return 0;
    }

    @Override
    public String setErrorString() {
        return null;
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
        onError(errorCode,errorDesc);
    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        onError(errorCode,errorDesc);
    }

    @Override
    public void onError(int errorCode, String errorDesc) {
        LogUtil.e(getClass(),"showServerError: error code = "
                + errorCode + " & error desc = " + errorDesc );
        showProgress(false);
        if (adapter.getData().size() > 0){
            if (!TextUtils.isEmpty(setErrorString())){

                Snackbar.make(fab,setErrorString(),Snackbar.LENGTH_SHORT).show();

            }else {
                Snackbar.make(fab,errorDesc,Snackbar.LENGTH_SHORT).show();

            }
            return;
        }

        if (setErrorImageResource() != 0){
            errorImage.setImageDrawable(getResources().getDrawable(setErrorImageResource()));
        }

        if (!TextUtils.isEmpty(setErrorString())){
            errorText.setText(setErrorString());
        }else {
            errorText.setText(errorDesc);
        }
        errorLayout.setVisibility(View.VISIBLE);

    }


    /**
     * @Method: initBaseView
     * @author create by Tang
     * @date date 16/10/25 下午4:38
     * @Description: 初始化View
     */
    private void initBaseView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        baseRecyclerView = (RecyclerView) findViewById(R.id.base_recycler_view) ;
        baseRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_refresh_layout);

        errorLayout = (LinearLayout) findViewById(R.id.base_error_layout);
        errorImage = (ImageView) findViewById(R.id.base_error_img);
        errorText = (TextView) findViewById(R.id.base_error_text);

        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled());

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
                    }
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    lastVisibleItem = findMax(lastPositions);
                }
            }
        });

        baseRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        baseRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MvpListActivity.this.onRefresh();
            }
        });


    }

    @Override
    public void startActivity(Intent intent) {
        try{

            super.startActivity(intent);

        }catch (ActivityNotFoundException e){
            Snackbar.make(baseRefreshLayout,"找不到对应的页面",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try{

            super.startActivityForResult(intent, requestCode);

        }catch (ActivityNotFoundException e){
            Snackbar.make(baseRefreshLayout,"找不到对应的页面",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
        }

    }

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


    /**
     * 抽象方法，子必须实现setLayoutManager()、setAdapter()、setPresenter()方法
     * setParams()可以返回空
     */
    public abstract RecyclerView.LayoutManager setLayoutManager();

    public abstract BaseListAdapter setAdapter();

    public abstract BasePaginationPresenter setPresenter();

    public abstract BasePaginationParams setParams();

}
