package com.mvp.framework.utils;

import java.util.List;

/**
 * @ClassName: ListUtils
 * @author create by Tang
 * @date date 16/10/19 下午5:22
 * @Description: List工具类
 */

public class ListUtils {

    /**
     * @Method: replaceAssign
     * @author create by Tang
     * @date date 16/10/19 下午5:26
     * @Description: TODO
     * @param start 替换的起始位置
     * @param oldList 需要替换数据的列表
     * @param newList 替换到toList的新数据
     */
    public static final <T> void replaceAssign(int start ,List<T> oldList,List<T> newList ){

        for (int i = start; i < start + newList.size(); i++){
            oldList.set(i,newList.get(i - start));
        }
    }
}
