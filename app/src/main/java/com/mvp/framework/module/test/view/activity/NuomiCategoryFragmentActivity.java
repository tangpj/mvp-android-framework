package com.mvp.framework.module.test.view.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.activity.BaseActivity;

/**
 * @ClassName: NuomiCategoryFragmentActivity
 * @author create by Tang
 * @date date 16/10/28 上午11:20
 * @Description: TODO
 */

public class NuomiCategoryFragmentActivity extends BaseActivity{
    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup parent) {
        setTitle("糯米分类Fragment");
        View view = inflater.inflate(R.layout.activity_nuomi_category_fragment,parent,false);
        return view;
    }
}
