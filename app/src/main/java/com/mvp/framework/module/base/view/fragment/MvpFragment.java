package com.mvp.framework.module.base.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.iview.IErrorInfo;
import com.mvp.framework.module.base.view.iview.IMvpFragment;
import com.mvp.framework.module.base.view.iview.IMvpView;
import com.mvp.framework.utils.LogUtil;

/**
 * @ClassName: MvpFragment
 * @author create by Tang
 * @date date 16/10/20 上午10:38
 * @Description: 基础的Fragment需继承此类
 */

public abstract class MvpFragment extends Fragment implements
        IMvpView,IMvpFragment,IErrorInfo {

    //默认进度条
    public static final int PROGRESS_TYPE_DEFAULT = 1;

    //对话框进度条
    public static final int PROGRESS_TYPE_DIALOG = 2;

    //下拉进度条
    public static final int PROGRESS_TYPE_DROP_DOWN = 3;

    //默认对加载进度条
    private int progressType;

    private FrameLayout contentViewGroup;
    private View contentView;

    private SwipeRefreshLayout refreshLayout;
    private ProgressBar defaultProgress;
    private ProgressDialog dialogProgress ;

    //错误显示View
    private LinearLayout errorLayout;
    private ImageView errorImage;
    private TextView errorText;
    private boolean isSucceed;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_mvp,container,false);
        initView(inflater,container,savedInstanceState,contentView);
        return contentView;
    }

    @Override
    public void showProgress(boolean show) {
        switch (progressType){
            case PROGRESS_TYPE_DEFAULT:
                if (show){
                    if (contentView != null){
                        contentView.setVisibility(View.GONE);
                    }
                    defaultProgress.setVisibility(View.VISIBLE);
                }else {
                    defaultProgress.setVisibility(View.GONE);
                    if (contentView != null){
                        contentView.setVisibility(View.VISIBLE);
                    }

                }
                break;

            case PROGRESS_TYPE_DIALOG:
                if(show){
                    dialogProgress.show();
                }else {
                    dialogProgress.dismiss();
                }

                break;

            case PROGRESS_TYPE_DROP_DOWN:
                refreshLayout.setRefreshing(show);
                break;
        }
    }

    @Override
    public void showNetworkError(int errorCode, String errorDesc, String ApiInterface) {
        showProgress(false);
        LogUtil.e(getClass(), "showNetworkError: " + ApiInterface);

        onError(errorCode,errorDesc);
    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        onError(errorCode,errorDesc);
    }

    @Override
    public void showSucceed(boolean isSucceed) {
        this.isSucceed = isSucceed;
        contentView.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void onReconnection() {
        // 子类中需要覆盖该方法，处理重连事件
        if (progressType == PROGRESS_TYPE_DEFAULT){
            errorLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setProgressType(int progressType) {
        this.progressType = progressType;
        switch (progressType){
            case PROGRESS_TYPE_DEFAULT:
                refreshLayout.setEnabled(false);
                errorLayout.setEnabled(true);
                break;

            case PROGRESS_TYPE_DIALOG:

                refreshLayout.setEnabled(false);
                errorLayout.setEnabled(true);
                break;

            case PROGRESS_TYPE_DROP_DOWN:
                refreshLayout.setEnabled(true);
                errorLayout.setEnabled(false);
                break;
        }
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
        showProgress(false);
        LogUtil.e(getClass(), "showServerError: error code = "
                + errorCode + " & error desc = " + errorDesc);

        switch (progressType) {

            case PROGRESS_TYPE_DEFAULT:
                if (setErrorImageResource() != 0) {
                    errorImage.setImageDrawable(getResources().getDrawable(setErrorImageResource()));
                }

                if (!TextUtils.isEmpty(errorDesc)) {
                    errorText.setText(errorDesc);
                }
                contentView.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                break;

            case PROGRESS_TYPE_DIALOG:
                contentView.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                break;

            case PROGRESS_TYPE_DROP_DOWN:
                if (setErrorImageResource() != 0) {
                    errorImage.setImageDrawable(getResources().getDrawable(setErrorImageResource()));
                }

                if (!TextUtils.isEmpty(errorDesc)) {
                    errorText.setText(errorDesc);
                }

                //如果首次获取数据成功，刷新后获取失败的话，则不显示错误信息
                if (!isSucceed) {
                    contentView.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.VISIBLE);
                }else {
                    Snackbar.make(getView(),errorDesc,Snackbar.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState,View view){

        contentViewGroup = (FrameLayout) view.findViewById(R.id.content_base);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        defaultProgress = (ProgressBar) view.findViewById(R.id.base_progress_bar);

        dialogProgress = new ProgressDialog(getActivity());
        dialogProgress.setTitle("加载中...");

        errorLayout = (LinearLayout) view.findViewById(R.id.base_error_layout);
        errorImage = (ImageView) view.findViewById(R.id.base_error_img);
        errorText = (TextView) view.findViewById(R.id.base_error_text);

        setProgressType(PROGRESS_TYPE_DEFAULT);

        contentViewGroup = (FrameLayout) view.findViewById(R.id.content_base);
        contentView = onCreateContentView(inflater,container,savedInstanceState);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        contentViewGroup.addView(contentView,lp);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onReconnection();
            }
        });

        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReconnection();
            }
        });

    }
}
