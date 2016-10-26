package com.mvp.framework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.params.NuoMiShopInfoParams;
import com.mvp.framework.module.test.params.WeatherParams;
import com.mvp.framework.module.test.presenter.NuoMiShopInfoPresenter;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.activity.NuomiCategoryActivity;
import com.mvp.framework.module.test.view.activity.TestActivity;
import com.mvp.framework.module.test.view.iview.INuoMiShopInfoMvpView;
import com.mvp.framework.module.test.view.iview.IWeatherMvpView;
import com.mvp.framework.utils.LogUtil;


public class MainActivity extends AppCompatActivity implements IWeatherMvpView
        ,INuoMiShopInfoMvpView {

    private static final String TAG = "MainActivity";

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;
    private Button nuoMiShopInfoBtn;
    private Button testBtn;

    private WeatherPresenter presenter;
    private NuoMiShopInfoPresenter nuoMiShopInfoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        presenter = new WeatherPresenter(this);
        nuoMiShopInfoPresenter = new NuoMiShopInfoPresenter(this);


    }

    private void initView(){
        weatherBtn = (Button) findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) findViewById(R.id.nuo_mi_category_btn);
        nuoMiShopInfoBtn = (Button) findViewById(R.id.nuo_mi_shop_info_btn);
        testBtn = (Button) findViewById(R.id.go_test) ;

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherParams params = new WeatherParams();
                params.cityname = "北京";
                presenter.accessServer(params);
            }
        });

        nuoMiCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NuomiCategoryActivity.class);
                startActivity(intent);
            }
        });


        nuoMiShopInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuoMiShopInfoParams params = new NuoMiShopInfoParams();
                params.shop_id = "1745896";
                nuoMiShopInfoPresenter.accessServer(params);
            }
        });

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showNetworkError(int errorCode, String errorDesc, String ApiInterface) {

    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        LogUtil.e(MainActivity.class,"errorCode = " + errorCode + " & errorDesc = " + errorDesc);
        Toast.makeText(this,errorDesc,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showWeatherView(WeatherBean data) {
        Toast.makeText(this,data.weather,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNuoMiShopInfoView(NuoMiShopInfoBean nuoMiShopInfo) {
        Toast.makeText(this,"商户名称： " + nuoMiShopInfo.shopName,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSucceed(boolean isSucceed) {

    }
}
