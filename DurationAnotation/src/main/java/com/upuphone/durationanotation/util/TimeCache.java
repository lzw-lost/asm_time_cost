package com.upuphone.durationanotation.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class TimeCache {
    private static Map<String, Long> map = new HashMap<>();

    public static void getTimeLog(long start, String tag, String className, String methodName) {
        Log.i(tag, "time-cost======== " + className + "/ " + methodName + "  " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void logStart(String tag, String startClassName, String startMethodName, String endClassName, String endMethodName) {
        map.put(startClassName + startMethodName + endClassName + endMethodName, System.currentTimeMillis());
        Log.i(tag, "time-duration== " + startClassName + "/ " + startMethodName + "  startTime=" + System.currentTimeMillis());
    }

    public static void getTimeLogDuration(String tag, String startClassName, String startMethodName, String endClassName, String endMethodName) {
        long endTime = System.currentTimeMillis();
        Long startTime = map.get(startClassName + startMethodName + endClassName + endMethodName);
        Log.i(tag, "time-duration== " + endClassName + "/ " + endMethodName + "  timeCost=" + (endTime - startTime) + "ms" + "  endTime=" + endTime);
    }
}


