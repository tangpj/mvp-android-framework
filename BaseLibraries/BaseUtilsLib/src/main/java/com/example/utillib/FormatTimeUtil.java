package com.example.utillib;

/**
 * @ClassName: FormatTimeUtil
 * @author create by Tang
 * @date date 16/8/22 下午2:42
 * @Description: 时间格式转换工具类
 */
public class FormatTimeUtil {

    //把秒转换成00:00:00的格式
    public static String secToTime(int time){
        int minute = 0;
        int second = 0;
        int hour = 0;
        String timeStr = null ;
        if (time <= 0){
            return "00:00:00";
        }else {
            minute = time / 60;
            if (minute < 60){
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            }else {
                hour = time / 3600;
                minute = (time % 3600) / 60;
                second = (time % 3600) % 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    //把0转换成00的格式
    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
