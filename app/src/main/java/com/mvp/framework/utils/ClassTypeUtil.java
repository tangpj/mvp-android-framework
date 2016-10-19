package com.mvp.framework.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ClassName: ClassTypeUtil
 * @author create by Tang
 * @date date 16/10/19 上午10:59
 * @Description: TODO
 */

public class ClassTypeUtil {
    /**
     * ParameterizedType对象，对于Object、接口和原始类型返回null，对于数组class则是返回Object.class。
     * ParameterizedType是表示带有泛型参数的类型的Java类型，
     * JDK1.5引入了泛型之后，Java中所有的Class都实现了Type接口，ParameterizedType则是继承了Type接口，
     * 所有包含泛型的Class类都会实现这个接口。
     * @param raw
     * @param args
     * @return
     */
    public static ParameterizedType type(final Class raw, final Type... args){
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}
