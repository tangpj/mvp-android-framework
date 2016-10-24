package com.mvp.framework.module.base.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.iview.IBaseActivity;
import com.mvp.framework.module.base.view.iview.IBaseView;
import com.mvp.framework.utils.LogUtil;


/**
 * @ClassName: BaseActivity
 * @author create by Tang
 * @date date 16/10/24 下午1:55
 * @Description:
 * 普通Activity基类
 * 封装了Activity中相同的操作
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity,IBaseView{

    public static final int PROGRESS_TYPE_DEFAULT = 1;
    public static final int PROGRESS_TYPE_DIALOG = 2;

    //默认对加载进度条的样式为Dialog
    private int progressType = PROGRESS_TYPE_DEFAULT;

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBar ab;

    private ProgressBar defaultProgress;
    private ProgressDialog dialogProgress ;


    private LinearLayout errorLayout;


    private FrameLayout contentViewGroup;
    private View contentView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initBaseView(savedInstanceState);

    }

    private void initBaseView(Bundle savedInstanceState){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        contentViewGroup = (FrameLayout) findViewById(R.id.content_base);

        defaultProgress = (ProgressBar) findViewById(R.id.bas_progress_bar);

        dialogProgress = new ProgressDialog(this);
        dialogProgress.setTitle("加载中...");

        errorLayout = (LinearLayout) findViewById(R.id.base_error_layout);

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


        contentView = onCreateView(getLayoutInflater(),contentViewGroup,savedInstanceState);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        contentViewGroup.addView(contentView,lp);

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
    public FloatingActionButton getFloatingActionButton(){
        return fab;
    }

    @Override
    public void onReconnection() {
        // 子类中如果需要处理重连事件的话，需要实现该方法
    }

    @Override
    public void setTitle(String title) {
        ab.setTitle(title);
    }

    @Override
    public void setProgressType(int progressType) {
        this.progressType = progressType;
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
        }

    }

    @Override
    public void showNetworkError(int errorCode, String errorDesc, String ApiInterface) {
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
        LogUtil.e(getClass(), "showNetworkError: " + ApiInterface);
        contentView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        Toast.makeText(this,errorDesc,Toast.LENGTH_SHORT).show();
        LogUtil.e(getClass(),"showServerError: error code = "
                + errorCode + " & error desc = " + errorDesc );
        contentView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }

}
