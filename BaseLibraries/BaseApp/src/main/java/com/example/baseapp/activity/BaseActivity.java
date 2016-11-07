package com.example.baseapp.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseapp.R;
import com.example.baseapp.iview.IBaseActivity;


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

    private CoordinatorLayout contentViewGroup;
    private View contentView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initBaseView();

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

        contentViewGroup = (CoordinatorLayout) ((ViewGroup)
                findViewById(android.R.id.content)).getChildAt(0);
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT
                ,CoordinatorLayout.LayoutParams.MATCH_PARENT);
        lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());

        contentView = setContentView(getLayoutInflater(),contentViewGroup);

        contentViewGroup.addView(contentView,lp);
    }

    @Override
    public void startActivity(Intent intent) {
        try{

            super.startActivity(intent);

        }catch (ActivityNotFoundException e){
            Snackbar.make(contentViewGroup,"找不到对应的页面",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try{

            super.startActivityForResult(intent, requestCode);

        }catch (ActivityNotFoundException e){
            Snackbar.make(contentViewGroup,"找不到对应的页面",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
        }

    }
}
