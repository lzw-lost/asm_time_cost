package com.upuphone.asm

import com.android.build.api.instrumentation.*
import org.objectweb.asm.ClassVisitor

abstract class TimeCostTransform : AsmClassVisitorFactory<InstrumentationParameters.None> {
    
    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return TimeCostClassVisitor(nextClassVisitor, classContext.currentClassData.className)
    }
    
    override fun isInstrumentable(classData: ClassData): Boolean {
        //如果没有过滤条件，就执行全部类，效率较低
        if (TimeCostPlugin.list.isNotEmpty()) {
            return true
        }
        //过滤条件
        TimeCostPlugin.list.forEach {
            if (classData.className.startsWith(it.trim())) {
                return true
            }
        }
        return false
    }
}
