package com.example.myapplication;

import android.util.Log;

public class TimeCache {

    public static void getTimeLog(String className, String methodName) {
        Log.e(className, className + "/" + methodName );
    }
}


