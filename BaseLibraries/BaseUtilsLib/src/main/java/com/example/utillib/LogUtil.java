package com.example.utillib;

import android.util.Log;

/**
 * @ClassName: Log
 * @author create by Tang
 * @date date 16/8/22 下午2:43
 * @Description: 日志管理
 */
public class LogUtil {

    private static final int infoLevel = 0;
    private static final int debugLevel = 1;
    private static final int warnLevel = 2;
    private static final int errorLevel = 3;

    private static int level = 3 ; //决定显示的log等级

    public static void i(Class<?> clas, String msg){
        if (level >= infoLevel){
            Log.i(clas.getSimpleName(),msg);
        }
    }

    public static void d(Class<?> clas, String msg){
        if (level >= debugLevel){
            Log.d(clas.getSimpleName(),msg);
        }
    }

    public static void w(Class<?> clas, String msg){
        if (level >= warnLevel){
            Log.w(clas.getSimpleName(),msg);
        }
    }

    public static void e(Class<?> clas, String msg){
        if (level >= errorLevel){
            Log.e(clas.getSimpleName(),msg);
        }
    }
}
