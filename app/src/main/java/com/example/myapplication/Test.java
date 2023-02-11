package com.example.myapplication;

import com.upuphone.durationanotation.Duration;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * author : lzw
 * date   : 2023-2-4 14:52
 */
@Duration(tag = "runasone----")
public class Test {
    public static String log() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TimeCache.getTimeLog(start,"1","2","3");
        return "0";
    }

    // @Duration(tag = "123")
    public static String log3() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "0";
    }

    @Duration(tag = "456----")
    public static int log2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            if (i == 25) {
                return 1;
            }
        }
        return 0;
    }
}
