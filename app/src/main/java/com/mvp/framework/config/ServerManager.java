package com.mvp.framework.config;

/**
 * @ClassName: ServerManager
 * @author create by Tang
 * @date date 16/9/29 上午10:11
 * @Description: 服务器地址管理器
 */

public class ServerManager {

    public static final int DEFAULT_TIMEOUT_MS = 1500;   //网络超时时间

    public static final boolean SERVER_MODE = true; //等于true时使用生产环境，等于false使用测试环境

    public static final String SERVER_PRODUCTION
            = "http://apis.baidu.com";    //生产环境服务器地址

    public static final String SERVER_DEVELOPMENT
            = "http://apis.baidu.com";   //测试环境服务器地址


    public static final int COUNT = 10;  //分页取数据条数


    public static String getServerUrl(String api){
//        Log.d("aa", "getServerUrl: " + SERVER_PRODUCTION);
        if (SERVER_MODE){
            return SERVER_PRODUCTION  + api;
        }else{
            return SERVER_DEVELOPMENT + api;
        }
    }
}
