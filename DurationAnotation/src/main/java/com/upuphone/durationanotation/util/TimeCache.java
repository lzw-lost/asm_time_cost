package com.upuphone.durationanotation.util;

import android.util.Log;

public class TimeCache {

    public static void getTimeLog(long start, String tag, String className, String methodName) {
        Log.i(tag, className + "/" + methodName + "  " + (System.currentTimeMillis() - start));
    }
}


