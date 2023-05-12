package com.upuphone.asm.util;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * author : lzw
 * date   : 2023-5-12 14:42
 */
public class LogData {

    private String startClassName;
    private String startMethodName;
    private String endClassName;
    private String endMethodName;
    private long start;
    private String tag;

    public String getStartClassName() {
        return startClassName;
    }

    public void setStartClassName(String startClassName) {
        this.startClassName = startClassName;
    }

    public String getStartMethodName() {
        return startMethodName;
    }

    public void setStartMethodName(String startMethodName) {
        this.startMethodName = startMethodName;
    }

    public String getEndClassName() {
        return endClassName;
    }

    public void setEndClassName(String endClassName) {
        this.endClassName = endClassName;
    }

    public String getEndMethodName() {
        return endMethodName;
    }

    public void setEndMethodName(String endMethodName) {
        this.endMethodName = endMethodName;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
