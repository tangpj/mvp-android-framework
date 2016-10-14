package com.mvp.framework.module.test.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @ClassName: NuoMiShopInfoBean
 * @author create by Tang
 * @date date 16/10/14 下午2:11
 * @Description: 汇率转换后的信息
 */

public class NuoMiShopInfoBean {

    //商户id
    @SerializedName("shop_id")
    public String shopId;

    //城市id
    @SerializedName("city_id")
    public String cityId;

    //商户名称
    @SerializedName("shop_name")
    public String shopName;

    //商户PC的url
    @SerializedName("shop_url")
    public String shopUrl;

    //商户移动端url
    @SerializedName("shop_murl")
    public String shopMurl;

    //商户地址
    public String address;

    //行政区id
    @SerializedName("district_id")
    public String districtId;

    //商圈id
    @SerializedName("bizarea_Id")
    public String bizarea_id;

    //商户电话
    public String phone;

    //营业时间
    @SerializedName("open_time")
    public String open_time;

    //经度
    public float longitude;

    //纬度
    public float latitude;

    //交通信息
    @SerializedName("traffic_info")
    public String trafficInfo;

}
