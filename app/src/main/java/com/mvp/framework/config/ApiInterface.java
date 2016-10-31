package com.mvp.framework.config;

/**
 * @ClassName: ApiInterface
 * @author create by Tang
 * @date date 16/9/29 上午10:07
 * @Description:
 * 接口配置文件,建议通过接口配置文件管理接口
 * 可以在以内部类的形式区分接口版本
 */

public class ApiInterface {

    //查询城市的天气状况
    public static final String WEATER = "/apistore/weatherservice/cityname?cityname=北京";

    //百度糯米团单分类信息
    public static final String NUO_MI_CATEGOR = "/baidunuomi/openapi/categories";

    //获取糯米商家信息
    public static final String NUO_MI_SHOP_INFO = "/baidunuomi/openapi/shopinfo?shop_id=1745896";



}
