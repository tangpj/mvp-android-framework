package com.mvp.framework.module.base.presenter;

import com.mvp.framework.config.ServerManager;
import com.mvp.framework.module.base.model.BaseVolleyModel;
import com.mvp.framework.module.base.model.imodel.IBaseModel;
import com.mvp.framework.module.base.params.BasePaginationParams;
import com.mvp.framework.module.base.presenter.ipresenter.IBasePaginationPresenter;
import com.mvp.framework.module.base.view.IBaseView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


/**
 * @ClassName: BasePaginationPresenter
 * @author create by Tang
 * @date date 16/9/29 下午2:14
 * @Description:
 * @T: 请求参数类（http中的params）
 * @S: 返回队列的数据项实体类（bean中的实体类）
 */
public abstract class BasePaginationPresenter<T extends BasePaginationParams,S>
        implements IBasePaginationPresenter<T,S> {

    public abstract void serverResponse(String response);

    private IBaseView baseView;
    private IBaseModel baseModel;
    private T params;
    private boolean isPagination = true;   //默认为分页

    private ArrayList<S> dataList = new ArrayList<>();
    private ArrayList<S> currentList = new ArrayList<>();

    protected BasePaginationPresenter(IBaseView baseView){
        this.baseView = baseView;
        this.baseModel = new BaseVolleyModel(this);
    }



    @Override
    public void refresh(T params) {
        dataList.clear();
        accessServer(params);
    }

    @Override
    public void currentRefresh() {
        dataList.removeAll(currentList);
        accessServer(params);
    }

    @Override
    public void setPaginationEnable(boolean enable) {
        this.isPagination = enable;
    }

    @Override
    public ArrayList getData() {
        return dataList;
    }

    @Override
    public void setData(ArrayList list) {
        currentList = list;
        this.dataList.addAll(currentList);
    }

    @Override
    public int getListSize() {
        return dataList.size();
    }

    @Override
    public Map getParams() {
        if (params != null){
            params.count = ServerManager.COUNT;
            params.page = (int) Math.ceil((double)
                    getListSize() * 1.0 / ServerManager.COUNT) + 1;
            return  params.toMap();
        }else {
            return null;
        }
    }

    @Override
    public IBaseModel getModel(){
        return baseModel;
    }

    @Override
    public void accessServer(T params) {
        this.params = params;
        baseView.showProcess(true);
        baseModel.sendRequestToServer();
    }

    @Override
    public void accessSucceed(JSONObject response) {
        baseView.showProcess(false);
        serverResponse(String.valueOf(response));

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
