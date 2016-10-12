package com.mvp.framework.module.test.presenter;

import com.mvp.framework.config.ApiInterface;
import com.mvp.framework.module.base.presenter.BasePresenter;
import com.mvp.framework.module.base.response.BaiduBaseResponse;
import com.mvp.framework.module.base.view.IBaseView;
import com.mvp.framework.module.test.params.WeatherParams;
import com.mvp.framework.module.test.view.iview.IWeatherView;

/**
 * @ClassName: WeatherPresenter
 * @author create by Tang
 * @date date 16/10/12 下午2:35
 * @Description: 天气查询
 */
public class WeatherPresenter extends BasePresenter<WeatherParams,Object>{

    private IWeatherView weatherView;

    protected WeatherPresenter(IWeatherView weatherView) {
        super(weatherView);
        this.weatherView = weatherView;
        getModel().setApiInterface(ApiInterface.WEATER);
    }

    @Override
    public void serverResponse(BaiduBaseResponse response) {
    }
}
