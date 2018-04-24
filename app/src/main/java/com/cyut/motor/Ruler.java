package com.cyut.motor;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Ruler {
    //多解析度
    public int Width;
    public int Height;
    public int densityDpi;
    public static Typeface typeFace;
    private android.content.Context Context;

    // Width & Height
    public Ruler(android.content.Context Context) {
        this.Context = Context;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) this.Context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        Width = dm.widthPixels;
        Height = dm.heightPixels;
        densityDpi = dm.densityDpi;
//		Log.e("", dm.heightPixels+"--"+dm.widthPixels+"--"+dm.densityDpi+"--"+dm.density+"--"+dm.xdpi+"--"+dm.ydpi);
//		Toast.makeText(Context, ""+dm.densityDpi, Toast.LENGTH_LONG).show();
    }

    public int getW(double Per) {
        if (Per == -1)
            return -1;
        else if (Per == -2)
            return -2;
        return (int) ((Per > 100.0) ? Width : ((Width * Per) / 100));
    }

    public int getH(double Per) {
        if (Per == -1)
            return -1;
        else if (Per == -2)
            return -2;
        return (int) ((Per > 100.0) ? Height : ((Height * Per) / 100));
    }

    public int getCW(int W, double Per) {
        return (int) ((Per > 100.0) ? W : ((W * Per) / 100));
    }

    public int getCH(int H, double Per) {
        return (int) ((Per > 100.0) ? H : ((H * Per) / 100));
    }

    public Double getdeviceScale() {
        Double h = new Double(Height);
        Double w = new Double(Width);
        return new Double(h / w);
    }

    public static boolean isPad(android.content.Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    public static int getDP(android.content.Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
