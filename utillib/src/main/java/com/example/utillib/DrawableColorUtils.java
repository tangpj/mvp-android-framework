package com.example.utillib;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * @ClassName: DrawableColorUtils
 * @author create by Tang
 * @date date 16/9/29 上午10:24
 * @Description: 修改图片颜色
 */
public class DrawableColorUtils {
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
