package com.upuphone.asm.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * author : lzw
 * date   : 2023-5-12 15:58
 */
public class TimeUtil {

    private static Map<String, Long> map = new ConcurrentHashMap<>();


    public static void putMethodStart(String startClassName, String startmethodName, String tag, String endClassName, String endMethodName) {
        if (endClassName != null && endClassName.length() > 0 && endMethodName != null && endMethodName.length() > 0) {
            long start = System.currentTimeMillis();
            LogData data = new LogData();
            data.setStart(start);
            data.setStartClassName(startClassName);
            data.setStartMethodName(startmethodName);
            data.setTag(tag);
            map.put(endClassName + endMethodName, start);
        }
    }

    public static Long getStartTime(String className, String methodName) {
        if (className != null && className.length() > 0 && methodName != null && methodName.length() > 0) {
            String key = className + methodName;
            Long startTime = map.get(key);
            if (startTime != null) {
                map.remove(key);
                return startTime;
            }
        }
        return null;
    }
}
