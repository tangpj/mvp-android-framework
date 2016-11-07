package com.mvp.framework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "MainActivity";

    private Button weatherBtn;
    private Button nuoMiCategoryBtn;
    private Button nuoMiShopInfoBtn;
    private Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView(){
        weatherBtn = (Button) findViewById(R.id.weather_btn);
        nuoMiCategoryBtn = (Button) findViewById(R.id.nuo_mi_category_btn);
        nuoMiShopInfoBtn = (Button) findViewById(R.id.nuo_mi_shop_info_btn);
        testBtn = (Button) findViewById(R.id.go_test) ;


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
