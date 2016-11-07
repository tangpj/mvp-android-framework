package com.mvp.framework.module.bean;

/**
 * @ClassName: Status
 * @author create by Tang
 * @date date 16/9/29 下午2:27
 * @Description:
 * 服务器相应状态
 */

public class Status {

    //服务器是否正确响应
    public boolean success;

    //服务器响应错误码
    public String errorCode;

    //服务器响应错误说明
    public String errorDesc;
}
