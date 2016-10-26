package com.mvp.framework.module.test.view.iview;

import com.mvp.framework.module.base.view.iview.IBaseMvpView;
import com.mvp.framework.module.base.view.iview.IMvpView;
import com.mvp.framework.module.test.bean.WeatherBean;

/**
 * @ClassName: IWeatherMvpView
 * @author create by Tang
 * @date date 16/10/12 下午2:36
 * @Description: TODO
 */

public interface IWeatherMvpView extends IMvpView {

    void showWeatherView(WeatherBean data);
}
