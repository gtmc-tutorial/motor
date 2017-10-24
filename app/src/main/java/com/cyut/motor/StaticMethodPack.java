package com.cyut.motor;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.io.File;

/**
 * Created by CAMac on 2016/12/21.
 */

public class StaticMethodPack {

    /**
     * 回傳是否為PAD
     *
     * @param context Context
     * @return true if is pad
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 回傳縮小後的bitmap(若需要，否則回傳原圖)
     *
     * @param bitmap   原圖
     * @param toHeight 目標高度
     * @return Bitmap
     */
    public static Bitmap bitmapResize(Bitmap bitmap, int toHeight) {
        // 取得原始圖檔的bitmap與寬高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height < toHeight) return bitmap;
        float scale = ((float) toHeight) / height;

        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * scale), toHeight, true);
        bitmap.recycle();

        return newBitmap;
    }

    public static boolean isNetworkConnecting(Context context) {
        ConnectivityManager CM = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = CM.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}
