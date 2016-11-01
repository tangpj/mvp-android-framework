package com.mvp.framework.module.base.presenter;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.mvp.framework.module.base.model.BaseModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePresenter;
import com.mvp.framework.module.base.response.BaseResponse;
import com.mvp.framework.module.base.view.iview.IBaseMvpView;
import com.mvp.framework.module.base.view.iview.IMvpView;
import com.mvp.framework.utils.ClassTypeUtil;


import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @ClassName: BasePresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:14
 * @Description: 普通presenter基类
 * 返回的data里面的数据为非队列型的
 * @Params: 提交参数类
 * @Data: 服务器返回数据
 */
public abstract class BasePresenter<Params extends BaseParams,Data>
        implements IBasePresenter<Params> {

    public abstract void serverResponse(Data data);

    private IBaseModel baseModel;
    private IMvpView mvpView;
    private Params params;
    private Class<Data> clazz;


    /**
     * @Method: BasePresenter
     * @author create by Tang
     * @date date 16/10/14 下午5:32
     * @Description: BaseResponse中Data为空使用该构造方法
     */
    public BasePresenter(IMvpView mvpView) {
        this.mvpView = mvpView;
        this.baseModel = new BaseModel(this);
    }

    /**
     * @param clazz 数据的类型
     * @Method: BasePresenter
     * @author create by Tang
     * @date date 16/10/14 下午5:32
     * @Description: BaseResponse中Data不为空使用该构造方法
     */
    public BasePresenter(IMvpView mvpView, Class<Data> clazz) {
        this.mvpView = mvpView;
        this.baseModel = new BaseModel(this);
        this.clazz = clazz;
    }

    @Override
    public Map getParams() {
        if (params != null) {
            return params.toMap();
        } else {
            return null;
        }
    }

    @Override
    public IBaseModel getModel() {
        return baseModel;
    }


    @Override
    public void accessServer(Params params) {
        this.params = params;
        mvpView.showProgress(true);
        baseModel.sendRequestToServer();
    }

    @Override
    public void accessSucceed(JSONObject response) {
        mvpView.showProgress(false);
        Gson gson = new Gson();
        BaseResponse<Data> mResponse;
        if(clazz != null){
            ParameterizedType parameterized = ClassTypeUtil.type(BaseResponse.class, clazz);
            Type type = $Gson$Types.canonicalize(parameterized);
            mResponse = gson.fromJson(String.valueOf(response), type);
        }else {
            mResponse = gson.fromJson(String.valueOf(response),BaseResponse.class);
        }


        /**
         * 在实际设计系统的时候，通过状态码来判断服务器是否正确响应
         * 如果响应错误，可以在这里直接通知view层错误情况
         * 以下为根据百度api的数据格式设计的回调处理
         * errorNum = 0 时，响应成功
         */

        if (mResponse.errNum == 0) {
            serverResponse(mResponse.data);
            mvpView.showSucceed(true);
        } else {
            mvpView.showServerError(mResponse.errNum, mResponse.errMsg);
        }
    }


    @Override
    public void volleyError(int errorCode, String errorDesc, String apiInterface) {
        mvpView.showNetworkError(errorCode, errorDesc, apiInterface);
    }

    @Override
    public void cancelRequest() {
        baseModel.cancelRequest();
    }


}
