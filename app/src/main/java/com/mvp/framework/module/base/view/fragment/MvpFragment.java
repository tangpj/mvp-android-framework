package com.mvp.framework.module.base.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.iview.IBaseMvpView;

/**
 * @ClassName: MvpFragment
 * @author create by Tang
 * @date date 16/10/20 上午10:38
 * @Description: 基础的Fragment需继承此类
 */

public class MvpFragment extends Fragment implements IBaseMvpView {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_mvp,container,false);
        initView(contentView);
        return contentView;
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showNetworkError(int errorCode, String errorDesc, String ApiInterface) {

    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {

    }

    private void initView(View view){


    }


}
