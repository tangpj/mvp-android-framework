package com.mvp.framework.module.base.view.fragment;

import android.app.Fragment;

import com.mvp.framework.module.base.view.iview.IBaseView;

/**
 * @ClassName: BaseFragment
 * @author create by Tang
 * @date date 16/10/20 上午10:38
 * @Description: 基础的Fragment需继承此类
 */

public class BaseFragment extends Fragment implements IBaseView{

    @Override
    public void showProcess(boolean show) {

    }

    @Override
    public void showVolleyError(int errorCode, String errorDesc, String ApiInterface) {

    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {

    }
}
