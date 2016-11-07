package com.example.basemvplib.response;

import com.example.basemvplib.response.BaseResponse;

/**
 * @ClassName: BasePaginationResponse
 * @author create by Tang
 * @date date 16/10/24 下午4:08
 * @Description: 列表型数据返回类
 */

public class BasePaginationResponse<Data> extends BaseResponse<Data> {

    public int nextPage;
}
