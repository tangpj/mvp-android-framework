package com.mvp.framework.module.test.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.activity.BaseActivity;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.params.NuoMiShopInfoParams;
import com.mvp.framework.module.test.params.WeatherParams;
import com.mvp.framework.module.test.presenter.NuoMiCategoryPresenter;
import com.mvp.framework.module.test.presenter.NuoMiShopInfoPresenter;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryView;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoView;
import com.mvp.framework.module.test.view.iview.IWeatherView;

import java.util.List;

/**
 * @ClassName: TestActivity
 * @author create by Tang
 * @date date 16/10/14 下午2:21
 * @Description: 测试BaseActivity的效果
 */

public class TestActivity extends BaseActivity implements IWeatherView
        ,INuoMiShopInfoView {

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;
    private Button nuoMiShopInfoBtn;


    private WeatherPresenter presenter;
    private NuoMiShopInfoPresenter nuoMiShopInfoPresenter;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_test,container,false);
        weatherBtn = (Button) view.findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) view.findViewById(R.id.nuo_mi_category_btn);
        nuoMiShopInfoBtn = (Button) view.findViewById(R.id.nuo_mi_shop_info_btn);

        presenter = new WeatherPresenter(this);
        nuoMiShopInfoPresenter = new NuoMiShopInfoPresenter(this);

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressType(PROGRESS_TYPE_DIALOG);
                WeatherParams params = new WeatherParams();
                params.cityname = "北京";
                presenter.accessServer(params);
            }
        });

        nuoMiCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,NuomiCategoryActivity.class);
                startActivity(intent);
            }
        });


        nuoMiShopInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressType(PROGRESS_TYPE_DEFAULT);
                NuoMiShopInfoParams params = new NuoMiShopInfoParams();
                params.shop_id = "1745896";
                nuoMiShopInfoPresenter.accessServer(params);
            }
        });

        setTitle("测试11");

        return view;
    }


    @Override
    public void showWeatherView(WeatherBean data) {
        Toast.makeText(this,data.weather,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo) {
        Toast.makeText(this,"商户名称： " + nuoMiShopInfo.shopName,Toast.LENGTH_SHORT).show();
    }
}
