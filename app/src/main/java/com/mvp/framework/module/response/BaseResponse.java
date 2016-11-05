package com.mvp.framework.module.response;

import com.google.gson.annotations.SerializedName;

/**
 * @ClassName: BaseResponse
 * @author create by Tang
 * @date date 16/8/22 下午3:55
 * @Description: 数据返回类，用于数据解析
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
