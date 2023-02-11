package com.upuphone.durationanotation.util;

import android.util.Log;

public class TimeCache {

    public static void getTimeLog(long start, String tag, String className, String methodName) {
        Log.i(tag, "time-cost======== " + className + "/ " + methodName + "  " + (System.currentTimeMillis() - start)+ "ms");
    }

    // public static void getTimeLog2() {
    //     Log.i("------", "========" );
    // }
    //
    // public static void getTimeLog4() {
    //     getTimeLog2();
    // }
}


