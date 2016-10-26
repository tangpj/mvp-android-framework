package com.mvp.framework.module.base.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.iview.IBaseActivity;

/**
 * @ClassName: BaseActivity
 * @author create by Tang
 * @date date 16/10/26 下午1:45
 * @Description: 普通的Activity可以继承该累
 * 不支持处理网络相关回调
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ActionBar ab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_base,null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(view,lp);
        initBaseView();

        CoordinatorLayout contentView = (CoordinatorLayout) view;
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

    private void initBaseView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,setFragment()).commit();
    }

}
