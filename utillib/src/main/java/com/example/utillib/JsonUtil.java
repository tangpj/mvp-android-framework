package com.example.utillib;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * @ClassName: JsonUtil
 * @author create by Tang
 * @date date 16/8/23 下午2:25
 * @Description: 封装Json处理类
 */
public class JsonUtil {

    public static String toJson(Object src){
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    public static <T> T fromJson(JSONObject jsonObject, Class<T> typeToSrc){
        Gson gson = new Gson();
        return gson.fromJson(String.valueOf(jsonObject),typeToSrc);
    }
}
