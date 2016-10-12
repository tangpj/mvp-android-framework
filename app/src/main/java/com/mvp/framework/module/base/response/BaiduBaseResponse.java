package com.mvp.framework.module.base.response;

import com.mvp.framework.module.base.bean.Status;

/**
 * @ClassName: BaiduBaseResponse
 * @author create by Tang
 * @date date 16/8/22 下午3:55
 * @Description:
 * 基础返回数据处理类
 * 一些基础的返回处理可以放在这里
 */
public class BaiduBaseResponse<T>{

    public String errNum;

    public String errMsg;

    public T data;

}
