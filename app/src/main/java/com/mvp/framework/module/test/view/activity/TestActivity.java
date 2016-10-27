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
import com.mvp.framework.module.base.view.activity.MvpActivity;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.params.NuoMiShopInfoParams;
import com.mvp.framework.module.test.presenter.NuoMiShopInfoPresenter;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoMvpView;
import com.mvp.framework.module.test.view.iview.IWeatherMvpView;

/**
 * @ClassName: TestActivity
 * @author create by Tang
 * @date date 16/10/14 下午2:21
 * @Description: 测试BaseActivity的效果
 */

public class TestActivity extends BaseActivity {

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;
    private Button nuoMiShopInfoBtn;

    //测试baseFragment
    private Button fWeatherBtn;
    private Button fNuoMiCategoryBtn;
    private Button fNuoMiShopInfoBtn;


    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.activity_test,container,false);
        weatherBtn = (Button) view.findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) view.findViewById(R.id.nuo_mi_category_btn);
        nuoMiShopInfoBtn = (Button) view.findViewById(R.id.nuo_mi_shop_info_btn);

        fWeatherBtn = (Button) view.findViewById(R.id.f_weather_btn);
        fNuoMiCategoryBtn = (Button) view.findViewById(R.id.f_nuo_mi_category_btn);
        fNuoMiShopInfoBtn = (Button) view.findViewById(R.id.f_nuo_mi_shop_info_btn);

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,WeatherActivity.class);
                startActivity(intent);
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
//                NuoMiShopInfoParams params = new NuoMiShopInfoParams();
//                params.shop_id = "1745896";

                Intent intent = new Intent(TestActivity.this,NuoMiShopInfoActivity.class);
                startActivity(intent);
            }
        });



        fWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,WeatherFragmentActivity.class);
                startActivity(intent);
            }
        });

        fNuoMiCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        fNuoMiShopInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        setTitle("测试11");

        return view;
    }
}
