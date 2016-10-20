package com.mvp.framework.module.test.view.iview;

import com.mvp.framework.module.base.view.iview.IBaseView;
import com.mvp.framework.module.test.bean.WeatherBean;

/**
 * @ClassName: IWeatherView
 * @author create by Tang
 * @date date 16/10/12 下午2:36
 * @Description: TODO
 */

public interface IWeatherView extends IBaseView{

    void showWeatherView(WeatherBean data);
}
