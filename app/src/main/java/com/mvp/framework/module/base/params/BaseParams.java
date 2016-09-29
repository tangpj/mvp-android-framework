package com.mvp.framework.module.base.params;


import com.mvp.framework.module.base.params.annotations.ParamsName;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseMapParams
 * @author create by Tang
 * @date date 16/8/22 下午5:40
 * @Description:
 * 参数基类,继承不能多于两层
 */
public class BaseParams {


    /**
     * @Method: toMap()
     * @author create by Tang
     * @date date 16/8/23 下午3:20
     * @Description: 把实体类转换成Map类
     */
    public Map<String,Object> toMap() {
        Class<? extends BaseParams> clazz = this.getClass();
        Class<? extends Object> superClass = clazz.getSuperclass();

        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = superClass.getDeclaredFields();

        if (fields == null || fields.length == 0 ){
            return Collections.EMPTY_MAP;
        }

        Map<String, Object> params = new HashMap<>();
        try {

            for (Field field : fields) {
                if (field.get(this) != null){
                    field.setAccessible(true);
                    ParamsName paramsName = field.getAnnotation(ParamsName.class);
                    String key;
                    if (paramsName == null){
                        key = field.getName();
                    }else {
                        key = paramsName.value();
                    }
                    params.put(key , String.valueOf(field.get(this)));
                }
            }

            for (Field superField : superFields){
                if (superField.get(this) != null){
                    superField.setAccessible(true);
                    ParamsName superParamsName = superField.getAnnotation(ParamsName.class);
                    String superKey;
                    if (superParamsName == null){
                        superKey = superField.getName();
                    }else {
                        superKey = superParamsName.value();
                    }
                    params.put(superKey, String.valueOf(superField.get(this)));
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return params;
    }
}
