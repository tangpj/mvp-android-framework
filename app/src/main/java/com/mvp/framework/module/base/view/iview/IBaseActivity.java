package com.mvp.framework.module.base.view.iview;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * @ClassName: IBaseActivity
 * @author create by Tang
 * @date date 16/10/26 下午1:47
 * @Description: BaseActivity对应接口
 */

public interface IBaseActivity extends IActivity{

    /**
     * @Method: setFragment
     * @author create by Tang
     * @date date 16/10/26 下午1:49
     * @Description: 设置Fragment
     */
    @NonNull
    Fragment setFragment();
}
