package com.mvp.framework.module.test.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.activity.MvpActivity;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.iview.IWeatherView;

/**
 * @ClassName: WeatherActivity
 * @author create by Tang
 * @date date 16/10/26 下午4:03
 * @Description: TODO
 */

public class WeatherActivity extends MvpActivity implements IWeatherView {

    private TextView weather;

    private WeatherPresenter presenter;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setProgressType(PROGRESS_TYPE_DROP_DOWN);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_weather,container,false);
        weather = (TextView) view.findViewById(R.id.weather);
        presenter = new WeatherPresenter(this);
        presenter.accessServer(null);

        return view;
    }

    @Override
    public void onReconnection() {
        super.onReconnection();
        presenter.accessServer(null);
    }

    @Override
    public void showWeatherView(WeatherBean data) {
        weather.setText("城市：" + data.city + "\n日期：" + data.date + "\n天气状况：" + data.weather );
    }
}
