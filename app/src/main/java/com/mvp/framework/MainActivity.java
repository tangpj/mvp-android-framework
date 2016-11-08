package com.mvp.framework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.baseapp.activity.BaseActivity;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;
    private Button nuoMiShopInfoBtn;
    private Button testBtn;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        weatherBtn = (Button) view.findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) view.findViewById(R.id.nuo_mi_category_btn);
        nuoMiShopInfoBtn = (Button) view.findViewById(R.id.nuo_mi_shop_info_btn);
        testBtn = (Button) view.findViewById(R.id.go_test) ;


        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.testActivity.action");
//                intent.addCategory("com.example.testActivity.category");
//                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }
}
