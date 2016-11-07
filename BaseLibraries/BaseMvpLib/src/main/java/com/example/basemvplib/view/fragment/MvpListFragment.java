package com.example.basemvplib.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.basemvplib.R;
import com.example.basemvplib.params.BasePaginationParams;
import com.example.basemvplib.presenter.BasePaginationPresenter;
import com.example.basemvplib.view.adapter.BaseListAdapter;
import com.example.basemvplib.view.iview.IErrorInfo;
import com.example.basemvplib.view.iview.IMvpListFragment;
import com.example.basemvplib.view.iview.IMvpListView;
import com.example.utillib.LogUtil;


import java.util.List;

/**
 * @ClassName: MvpListFragment
 * @author create by Tang
 * @date date 16/10/20 上午10:38
 * @Description: 列表型Fragment需继承此类
 */

public abstract class MvpListFragment extends Fragment
        implements IMvpListView,IMvpListFragment,IErrorInfo {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView;
        if (mLayoutId != 0){
            contentView = inflater.inflate(mLayoutId,container,false);
        }else {
            contentView = inflater.inflate(R.layout.fragment_mvp_list,container,false);
        }
        presenter = setPresenter();

        onCreate(contentView);

        //自动刷新
        baseRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        onRefresh();

        return contentView;
    }

    @Override
    public void onCreate(View view) {
        initBaseView(view);
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
    public void isNextPage(int nextPage) {
        this.nextPage = nextPage;
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
    public void onError(int errorCode, String errorDesc) {
        LogUtil.e(getClass(),"showServerError: error code = "
                + errorCode + " & error desc = " + errorDesc );
        showProgress(false);
        if (adapter.getData().size() > 0){
            if (!TextUtils.isEmpty(setErrorString())){

                Snackbar.make(getView(),setErrorString(),Snackbar.LENGTH_SHORT).show();

            }else {
                Snackbar.make(getView(),errorDesc,Snackbar.LENGTH_SHORT).show();

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
    public void setData(List data) {
        if (errorLayout.getVisibility() == View.VISIBLE){
            errorLayout.setVisibility(View.GONE);
        }
        adapter.setData(data);
    }

    @Override
    public void reSetContentView(int layoutId) {
        this.mLayoutId = layoutId;
    }

    /**
     * @Method: initBaseView
     * @author create by Tang
     * @date date 16/10/28 上午10:08
     * @Description: 初始化Base View
     */
    private void initBaseView(View view){

        baseRecyclerView = (RecyclerView) view.findViewById(R.id.base_recycler_view) ;
        baseRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.base_refresh_layout);

        errorLayout = (LinearLayout) view.findViewById(R.id.base_error_layout);
        errorImage = (ImageView) view.findViewById(R.id.base_error_img);
        errorText = (TextView) view.findViewById(R.id.base_error_text);


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
                MvpListFragment.this.onRefresh();
            }
        });
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
    @NonNull
    public abstract RecyclerView.LayoutManager setLayoutManager();

    public abstract BaseListAdapter setAdapter();

    public abstract BasePaginationPresenter setPresenter();

    public abstract BasePaginationParams setParams();
}
