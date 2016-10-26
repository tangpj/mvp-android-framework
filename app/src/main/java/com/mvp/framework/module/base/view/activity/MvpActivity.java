package com.mvp.framework.module.base.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.iview.IMvpActivity;
import com.mvp.framework.module.base.view.iview.IMvpView;
import com.mvp.framework.module.base.view.iview.IResetErrorInfo;
import com.mvp.framework.utils.LogUtil;


/**
 * @ClassName: MvpActivity
 * @author create by Tang
 * @date date 16/10/24 下午1:55
 * @Description:
 * 非列表型MVP Activity基类
 * 实现了IBaseView接口
 */
public abstract class MvpActivity extends AppCompatActivity
        implements IMvpActivity,IMvpView,IResetErrorInfo{

    //默认进度条
    public static final int PROGRESS_TYPE_DEFAULT = 1;

    //对话框进度条
    public static final int PROGRESS_TYPE_DIALOG = 2;

    //下拉进度条
    public static final int PROGRESS_TYPE_DROP_DOWN = 3;

    //默认对加载进度条的样式为Dialog
    private int progressType;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBar ab;
    private SwipeRefreshLayout refreshLayout;

    private ProgressBar defaultProgress;
    private ProgressDialog dialogProgress ;


    //错误显示View
    private LinearLayout errorLayout;
    private ImageView errorImage;
    private TextView errorText;
    private boolean isSucceed;

    private FrameLayout contentViewGroup;
    private View contentView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initBaseView(savedInstanceState);

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
    public void onReconnection() {
        // 子类中需要覆盖该方法，处理重连事件
        if (progressType == PROGRESS_TYPE_DEFAULT){
            errorLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTitle(String title) {
        ab.setTitle(title);
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

    @NonNull
    @Override
    public boolean setDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    public FloatingActionButton getFloatingActionButton(){
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
    public void showProgress(boolean show) {
        switch (progressType){
            case PROGRESS_TYPE_DEFAULT:
                if (show){
                    contentView.setVisibility(View.GONE);
                    defaultProgress.setVisibility(View.VISIBLE);
                }else {
                    defaultProgress.setVisibility(View.GONE);
                    contentView.setVisibility(View.VISIBLE);

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
    public void onError(int errorCode, String errorDesc) {
        showProgress(false);
        LogUtil.e(getClass(),"showServerError: error code = "
                + errorCode + " & error desc = " + errorDesc );

        switch (progressType){

            case PROGRESS_TYPE_DEFAULT:
                if (setErrorImageResource() != 0){
                    errorImage.setImageDrawable(getResources().getDrawable(setErrorImageResource()));
                }

                if (!TextUtils.isEmpty(errorDesc)){
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
                if (setErrorImageResource() != 0){
                    errorImage.setImageDrawable(getResources().getDrawable(setErrorImageResource()));
                }

                if (!TextUtils.isEmpty(errorDesc)){
                    errorText.setText(errorDesc);
                }

                if (!isSucceed){
                    contentView.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.VISIBLE);
                }

                break;
        }
    }

    /**
     * @Method: initBaseView
     * @author create by Tang
     * @date date 16/10/26 上午10:29
     * @Description: 初始化Base view
     */
    private void initBaseView(Bundle savedInstanceState){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        contentViewGroup = (FrameLayout) findViewById(R.id.content_base);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        defaultProgress = (ProgressBar) findViewById(R.id.base_progress_bar);

        dialogProgress = new ProgressDialog(this);
        dialogProgress.setTitle("加载中...");

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

        setProgressType(PROGRESS_TYPE_DEFAULT);

        contentView = onCreateView(getLayoutInflater(),contentViewGroup,savedInstanceState);
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
