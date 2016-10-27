package com.mvp.framework.module.test.view.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.activity.BaseActivity;

/**
 * @ClassName: WeatherFragmentActivity
 * @author create by Tang
 * @date date 16/10/27 下午3:02
 * @Description: TODO
 */

public class WeatherFragmentActivity extends BaseActivity{


    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup parent) {
        setTitle("天气Fragment");
        View view = inflater.inflate(R.layout.activity_weather_fragment,parent,false);
        return view;
    }


}
