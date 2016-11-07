package com.example.basemvplib.params.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: ParamsName
 * @author create by Tang
 * @date date 16/8/23 下午3:51
 * @Description:
 * 用于继承BaseParams的实体类转换为Map时的Key Name
 * 如果没有,则默认为参数名
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ParamsName {

    /**
     * @Method: value()
     * @author create by Tang
     * @date date 16/8/23 下午3:55
     * @Description:
     * 返回Map的key Name,默认为参数名
     */
    String value();
}
