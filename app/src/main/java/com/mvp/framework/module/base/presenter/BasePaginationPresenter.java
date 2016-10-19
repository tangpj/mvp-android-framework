package com.mvp.framework.module.base.presenter;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.mvp.framework.config.ServerManager;
import com.mvp.framework.module.base.model.BaseModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePaginationPresenter;
import com.mvp.framework.module.base.response.BaseResponse;
import com.mvp.framework.module.base.view.IBaseView;
import com.mvp.framework.utils.ClassTypeUtil;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;


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

    /**
     * @Method: responseNextPage
     * @author create by Tang
     * @date date 16/10/19 下午4:56
     * @Description: 返回下一页数据
     */
    public abstract void resultNextPage(List<Bean> data);

    /**
     * @Method: responseAssignPage
     * @author create by Tang
     * @date date 16/10/19 下午4:56
     * @Description: 返回指定页页数据
     * @param page
     */
    public abstract void resultAssignPage(int page,List<Bean> data);

    private IBaseView baseView;
    private IBaseModel baseModel;
    private Params mParams;


    /**
     * @author create by Tang
     * @date date 16/10/19 下午3:06
     * @Description: 获取页面数据的类型
     * 下一页或指定页
     */
    //需要刷新的页数
    private int refreshPage = -1;

    //默认一次去数据为ServerManager.COUNT
    private int count = ServerManager.COUNT;

    //获取指定页数据
    private int mPage = -1;

    private List<Bean> dataList = new ArrayList<>();


    private Class<Bean> clazz;

    protected BasePaginationPresenter(IBaseView baseView,Class<Bean> clazz){
        this.baseView = baseView;
        this.baseModel = new BaseModel(this);
        this.clazz = clazz;
    }



    @Override
    public void refresh(Params params) {
        this.mParams = params;
        dataList.clear();
        getNextPage();
    }

    @Override
    public void getNextPage() {
        if (mParams == null){
            mParams = (Params) new BasePaginationParams();
            mParams.count = count;
            mParams.page = (int) Math.ceil((double)
                    dataList.size() * 1.0 / ServerManager.COUNT) + 1;
        }else {
            mParams.count = count;
            mParams.page = (int) Math.ceil((double)
                    dataList.size() * 1.0 / ServerManager.COUNT) + 1;
        }

        accessServer();
    }

    @Override
    public void refreshAssignPage(int page) {
        if (page > (int) Math.ceil((double) dataList.size() * 1.0 / ServerManager.COUNT)){

            //如果page大于当前的页数则加载下一页
            getNextPage();
        }else {
            if (mParams == null){
                mParams = (Params) new BasePaginationParams();
                mParams.count = count;
                mParams.page = mPage;
            }else {
                mParams.count = count;
                mParams.page = mPage;
            }
        }
        accessServer();
    }


    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public List getData() {
        return dataList;
    }

    @Override
    public void addData(ArrayList list) {
    }


    @Override
    public Map setParams() {

        if (mParams != null){
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
        baseView.showProcess(true);
        /**
         * 如果上一次请求没有完成，需要取消上次一次请求
         *  这样处理是为了防止获取的列表数据出错
         */
        cancelRequest();
        baseModel.sendRequestToServer();
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
        baseView.showProcess(false);
        ParameterizedType parameterized = ClassTypeUtil.type(BaseResponse.class
                , ClassTypeUtil.type(List.class,clazz));
        Type type = $Gson$Types.canonicalize(parameterized);
        BaseResponse<List<Bean>> mResponse = new Gson().fromJson(responseStr, type);


        if (mPage < 0){
            dataList.addAll(mResponse.data);
            resultNextPage(mResponse.data);
        }else {


            resultAssignPage(mPage,mResponse.data);
        }

    }


    @Override
    public void volleyError(int errorCode, String errorDesc, String ApiInterface) {
        baseView.showVolleyError(errorCode,errorDesc,ApiInterface);
    }

    @Override
    public void cancelRequest() {
        baseModel.cancelRequest();
    }

}
