package com.example.basemvplib.presenter;


import android.support.annotation.NonNull;

import com.example.basemvplib.model.VolleyModel;
import com.example.basemvplib.model.imodel.IBaseModel;
import com.example.basemvplib.params.BasePaginationParams;
import com.example.basemvplib.presenter.ipresenter.IBasePaginationPresenter;
import com.example.basemvplib.response.BasePaginationResponse;
import com.example.basemvplib.view.iview.IMvpListView;
import com.example.utillib.ClassTypeUtil;
import com.example.utillib.ListUtils;
import com.example.utillib.LogUtil;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: BasePaginationPresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:14
 * @Description:
 * @Params: 请求参数类（http中的params）
 * @Bean: 返回队列的数据项实体类（bean中的实体类）
 */
public abstract class BasePaginationPresenter<Params extends BasePaginationParams,Bean>
        implements IBasePaginationPresenter<Params,Bean> {

    public abstract void serverResponse(List<Bean> list);

    private IMvpListView baseView;
    private IBaseModel baseModel;
    private Params mParams;

    //默认一次去数据为10
    private int mCount = 10;

    //需要刷新的数据项位置
    private int mIndex = -1;

    //需要刷新的页码（根据mIndex计算）
    private int mPage;

    private List<Bean> dataList = new ArrayList<>();


    private Class<Bean> clazz;

    /**
     * @Method: BasePaginationPresenter
     * @author create by Tang
     * @date date 16/10/20 上午10:18
     * @Description: 构造方法
     * @param clazz 队列参数项的类型，不能为空
     */
    protected BasePaginationPresenter(@NonNull IMvpListView baseView, @NonNull Class<Bean> clazz){
        this.baseView = baseView;
        this.baseModel = new VolleyModel(this);
        this.clazz = clazz;
    }



    @Override
    public void refresh(Params params) {
        this.mParams = params;
        dataList.clear();
        loading();
    }

    @Override
    public void loading() {
        if (mParams == null){
            mParams = (Params) new BasePaginationParams();
            mParams.count = mCount;
            mParams.page = (int) Math.ceil((double)
                    dataList.size() * 1.0 / mCount) + 1;
        }else {
            mParams.count = mCount;
            mParams.page = (int) Math.ceil((double)
                    dataList.size() * 1.0 / mCount) + 1;
        }

        accessServer();
    }

    @Override
    public void refreshIndexPage(int index) {
        if (index > dataList.size()){
            //如果index超出数组长度则加载下一页
            loading();
        }else {
            /**
             * 注需要根据服务器实际情况来计算
             * 这里假设服务器第一页数据的下标为1
             * 如果下表为0，mPage = index / mCount;
             */
            mIndex = index;
            mPage = index / mCount + 1;
            if (mParams == null){
                mParams = (Params) new BasePaginationParams();
                mParams.count = mCount;
                mParams.page = mPage;
            }else {
                mParams.count = mCount;
                mParams.page = mPage;
            }
        }
        accessServer();
    }


    @Override
    public void setCount(int count) {
        this.mCount = count;
    }


    @Override
    public Map getParams() {

        if (mParams != null){
            LogUtil.d(getClass(), "getParams: " + mParams.toString());
            return mParams.toMap();
        }else {
            return null;
        }

    }

    @Override
    public IBaseModel getModel(){
        return baseModel;
    }



    @Override
    public void accessServer() {
        baseView.showProgress(true);
        /**
         * 如果上一次请求没有完成，需要取消上次一次请求
         *  这样处理是为了防止获取的列表数据出错
         */
        cancelRequest();
        getModel().sendRequestToServer();
    }

    /**
     * @Method: accessServer
     * @author create by Tang
     * @date date 16/10/19 下午3:56
     * @Description:
     * 在获取队列型中数据中弃用该方法，
     * 参数通过Refresh(Params params)方法传入
     */
    @Deprecated
    @Override
    public void accessServer(Params params) {

    }

    @Override
    public void accessSucceed(JSONObject response) {
        String responseStr = String.valueOf(response);
        baseView.showProgress(false);
        ParameterizedType parameterized = ClassTypeUtil.type(BasePaginationResponse.class
                , ClassTypeUtil.type(List.class,clazz));
        Type type = $Gson$Types.canonicalize(parameterized);
        BasePaginationResponse<List<Bean>> mResponse = new Gson().fromJson(responseStr, type);

        if (mResponse.errNum == 0){
            if (mIndex < 0){
                dataList.addAll(mResponse.data);
                baseView.isNextPage(mResponse.nextPage);
            }else {
                //计算出需要替换的第一个数据在dataList中的位置
                int start = (mPage - 1) * mCount;
                ListUtils.replaceAssign(start,dataList,mResponse.data);
                mIndex = -1;
            }
            serverResponse(dataList);
        }else {
            baseView.showServerError(mResponse.errNum,mResponse.errMsg);
        }



    }


    @Override
    public void volleyError(int errorCode, String errorDesc, String ApiInterface) {
        baseView.showNetworkError(errorCode,errorDesc,ApiInterface);
    }

    @Override
    public void cancelRequest() {
        getModel().cancelRequest();
    }

}
