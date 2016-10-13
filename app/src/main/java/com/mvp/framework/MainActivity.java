package com.mvp.framework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.bean.WeatherBean;
import com.mvp.framework.module.test.params.WeatherParams;
import com.mvp.framework.module.test.presenter.NuoMiCategoryPresenter;
import com.mvp.framework.module.test.presenter.WeatherPresenter;
import com.mvp.framework.module.test.view.iview.INuoMiCategoryView;
import com.mvp.framework.module.test.view.iview.IWeatherView;
import com.mvp.framework.utils.LogUtil;

import java.util.List;


public class MainActivity extends AppCompatActivity implements IWeatherView ,INuoMiCategoryView{

    private static final String TAG = "MainActivity";

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;

    private WeatherPresenter presenter;

    private NuoMiCategoryPresenter nuoMiCategoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        presenter = new WeatherPresenter(this);
        nuoMiCategoryPresenter = new NuoMiCategoryPresenter(this);


    }

    private void initView(){
        weatherBtn = (Button) findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) findViewById(R.id.nuo_mi_category_btn);

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherParams params = new WeatherParams();
                params.cityname = "北京";
                presenter.accessServer(params);
            }
        });

        nuoMiCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuoMiCategoryPresenter.accessServer(null);
            }
        });


    }


    @Override
    public void showProcess(boolean show) {

    }

    @Override
    public void showVolleyError(int errorCode, String errorDesc, String apiInterface) {
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
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
    public void showNuoMiCategoryView(List<NuoMiCategoryBean> nuoMiCategoryList) {
        Toast.makeText(this,"成功",Toast.LENGTH_SHORT).show();

    }
}
