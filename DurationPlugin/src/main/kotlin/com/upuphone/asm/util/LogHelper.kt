package com.upuphone.asm.util

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * author : lzw
 * date   : 2023-2-10 16:50
 */
class LogHelper {
    companion object{
        private var openLog = true
    
        fun setOpenLog(openLog: Boolean) {
            this.openLog = openLog
        }
    
        fun log(message: String?) {
            if (openLog) {
                println(message)
            }
        }
    }
    
}