package com.upuphone.durationanotation.util;

import android.util.Log;

public class TimeCache {
    private static String myTag = "time-duration== ";

    public static void getTimeLog(long start, String tag, String className, String methodName) {
        Log.i(tag, "time-cost======== " + className + "/ " + methodName + "  " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void logStart(String tag, String className, String methodName, String endClassName, String endMethodName) {
        Log.i(tag, myTag + className + "/ " + methodName + "  start = " + System.currentTimeMillis() + " end= " + endClassName + "/ " + endMethodName);
    }

    public static void getTimeLog2(long start, String tag, String className, String methodName) {
        long endTime = System.currentTimeMillis();
        Log.i(tag, myTag + className + "/ " + methodName + "  " + (endTime - start) + "ms" + "  endTime=" + endTime);
    }
}


