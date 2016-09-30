package com.mvp.framework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.framework.module.test.params.TestParams;
import com.mvp.framework.module.test.presenter.TestPresenter;
import com.mvp.framework.module.test.view.iview.ITestView;
import com.mvp.framework.utils.LogUtil;


public class MainActivity extends AppCompatActivity implements ITestView{

    private static final String TAG = "MainActivity";

    private Button test;

    private TestPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new TestPresenter(this);
        test = (Button) findViewById(R.id.button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestParams params = new TestParams();
                params.cityname = "广州";
                presenter.accessServer(params);
            }
        });

    }

    @Override
    public void showTestView() {
        Toast.makeText(this,"获取成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProcess(boolean show) {

    }

    @Override
    public void showVolleyError(int errorCode, String errorDesc, String ApiInterface) {
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showServerError(String errorCode, String errorDesc) {
        LogUtil.e(MainActivity.class,"errorCode = " + errorCode + " & errorDesc = " + errorDesc);
        Toast.makeText(this,errorDesc,Toast.LENGTH_SHORT).show();
    }
}
