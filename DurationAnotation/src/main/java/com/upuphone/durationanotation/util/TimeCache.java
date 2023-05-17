package com.upuphone.durationanotation.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class TimeCache {
    private static Map<String, Long> map = new HashMap<>();
    private static boolean isDebug;

    public static void init(boolean debug) {
        isDebug = debug;
    }

    public static void getTimeLog(long start, String tag, String className, String methodName) {
        if(!isDebug){
            return;
        }
        Log.i(tag, "time-cost======== " + className + "/" + methodName + "  " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void logStart(String tag, String startClassName, String startMethodName, String endClassName, String endMethodName) {
        if(!isDebug){
            return;
        }
        map.put(startClassName + startMethodName + endClassName + endMethodName, System.currentTimeMillis());
        Log.i(tag, "time-duration== " + startClassName + "/" + startMethodName + "  startTime=" + System.currentTimeMillis());
    }

    public static void getTimeLogDuration(String tag, String startClassName, String startMethodName, String endClassName, String endMethodName) {
        if(!isDebug){
            return;
        }
        long endTime = System.currentTimeMillis();
        String key = startClassName + startMethodName + endClassName + endMethodName;
        Long startTime = map.get(key);
        if (startTime != null) {
            Log.i(tag, "time-duration== " + endClassName + "/" + endMethodName + "  timeCost=" + (endTime - startTime) + "ms" + "  endTime=" + endTime);
            // map.remove(key);
        } else {
            Log.i(tag, "time-duration== " + "无数据");
        }
    }
}


