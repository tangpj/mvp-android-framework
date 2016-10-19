package com.mvp.framework.module.base.response;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * @ClassName: BaseResponse
 * @author create by Tang
 * @date date 16/8/22 下午3:55
 * @Description:
 * 基础返回数据处理类
 * 一些基础的返回处理可以放在这里
 */
public class BaseResponse<Data>{

    @SerializedName(value = "errNum",alternate = {"status","errno"})
    public int errNum;

    @SerializedName(value = "errMsg",alternate = {"msg"})
    public String errMsg;

    /**
     * @author create by Tang
     * @date date 16/10/12 下午4:18
     * @Description: 兼容不同的情况
     * @restData: 百度api,天气data
     */
    @SerializedName(value = "data",alternate = {"retData","categories","shop"})
    public Data data;


}
