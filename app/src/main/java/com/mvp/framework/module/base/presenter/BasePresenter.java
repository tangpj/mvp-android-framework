package com.mvp.framework.module.base.presenter;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.mvp.framework.module.base.model.BaseVolleyModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.BaseParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePresenter;
import com.mvp.framework.module.base.response.BaseResponse;
import com.mvp.framework.module.base.view.IBaseView;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;
import com.mvp.framework.module.test.bean.NuoMiShopInfoBean;
import com.mvp.framework.utils.JsonUtil;


import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BasePresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:14
 * @Description: 普通presenter基类
 * @Params: 提交参数类
 * @Data: 服务器返回数据
 */
public abstract class BasePresenter<Params extends BaseParams,Data>
        implements IBasePresenter<Params> {

    public abstract void serverResponse(Data data);

    private IBaseModel baseModel;
    private IBaseView baseView;
    private Params params;
    private Class<Data> clazz;


    /**
     * @Method: BasePresenter
     * @author create by Tang
     * @date date 16/10/14 下午5:32
     * @Description: BaseResponse中Data为空使用该构造方法
     */
    public BasePresenter(IBaseView baseView){
        this.baseView = baseView;
        this.baseModel = new BaseVolleyModel(this) ;
    }

    /**
     * @Method: BasePresenter
     * @author create by Tang
     * @date date 16/10/14 下午5:32
     * @Description: BaseResponse中Data不为空使用该构造方法
     * @param clazz 数据的类型
     */
    public BasePresenter(IBaseView baseView,Class<Data> clazz){
        this.baseView = baseView;
        this.baseModel = new BaseVolleyModel(this);
        this.clazz = clazz;
    }

    @Override
    public Map getParams(){
        if (params != null){
            return params.toMap();
        }else {
            return null;
        }
    }

    @Override
    public IBaseModel getModel(){
        return baseModel;
    }


    @Override
    public void accessServer(Params params) {
        this.params = params;
        baseView.showProcess(true);
        baseModel.sendRequestToServer();
    }

    @Override
    public void accessSucceed(JSONObject response) {
        baseView.showProcess(false);
        Gson gson = new Gson();
        BaseResponse<Data> mResponse;
        ParameterizedType parameterized = type(BaseResponse.class,clazz);
        Type type = $Gson$Types.canonicalize(parameterized);
        mResponse = gson.fromJson(String.valueOf(response),type);


        /**
         * 在实际设计系统的时候，通过状态码来判断服务器是否正确响应
         * 如果响应错误，可以在这里直接通知view层错误情况
         * 以下为根据百度api的数据格式设计的回调处理
         * errorNum = 0 时，响应成功
         */

        if (mResponse.errNum == 0){
            serverResponse(mResponse.data);
        }else {
            baseView.showServerError(mResponse.errNum,mResponse.errMsg);
        }
    }


    @Override
    public void volleyError(int errorCode, String errorDesc, String apiInterface) {
        baseView.showVolleyError(errorCode,errorDesc,apiInterface);
    }

    @Override
    public void cancelRequest() {
        baseModel.cancelRequest();
    }


    /**
     * ParameterizedType对象，对于Object、接口和原始类型返回null，对于数组class则是返回Object.class。
     * ParameterizedType是表示带有泛型参数的类型的Java类型，
     * JDK1.5引入了泛型之后，Java中所有的Class都实现了Type接口，ParameterizedType则是继承了Type接口，
     * 所有包含泛型的Class类都会实现这个接口。
     * @param raw
     * @param args
     * @return
     */
    public static ParameterizedType type(final Class raw,final Type... args){
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}
