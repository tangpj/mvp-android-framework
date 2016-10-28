package com.mvp.framework.module.test.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.fragment.MvpFragment;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.iview.IWeatherView;

/**
 * @ClassName: WeatherFragment
 * @author create by Tang
 * @date date 16/10/27 下午3:04
 * @Description: TODO
 */
public class WeatherFragment extends MvpFragment implements IWeatherView {

    private TextView weather;
    private WeatherPresenter presenter;

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather,container,false);
        setProgressType(PROGRESS_TYPE_DROP_DOWN);
        weather = (TextView) view.findViewById(R.id.weather);
        presenter = new WeatherPresenter(this);
        presenter.accessServer(null);
        return view;
    }

    @Override
    public void showWeatherView(WeatherBean data) {
        weather.setText("城市：" + data.city + "\n日期：" + data.date + "\n天气状况：" + data.weather );
    }

    @Override
    public void onReconnection() {
        super.onReconnection();
        presenter.accessServer(null);
    }
}
