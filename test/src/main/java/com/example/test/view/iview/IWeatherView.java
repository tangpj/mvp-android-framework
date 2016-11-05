package com.example.test.view.iview;

import com.mvp.framework.module.view.iview.IBaseMvpView;
import com.mvp.framework.module.view.iview.IMvpView;
import com.example.test.bean.WeatherBean;

/**
 * @ClassName: IWeatherView
 * @author create by Tang
 * @date date 16/10/12 下午2:36
 * @Description: TODO
 */

public interface IWeatherView extends IMvpView {

    void showWeatherView(WeatherBean data);
}
