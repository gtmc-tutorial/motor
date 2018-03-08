package com.cyut.motor;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by wubingyu on 2017/10/11.
 */

public class Util {
    //工具
    //test123456789.66666666667777777
    public static int getDP(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
