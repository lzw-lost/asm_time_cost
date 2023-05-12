package com.upuphone.asm

import com.android.build.api.instrumentation.*
import com.upuphone.asm.util.Constants
import org.objectweb.asm.ClassVisitor

abstract class TimeCostTransform : AsmClassVisitorFactory<InstrumentationParameters.None> {
    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return TimeCostClassVisitor(nextClassVisitor, classContext.currentClassData.className)
    }
    
    override fun isInstrumentable(classData: ClassData): Boolean {
        // if (classData.classAnnotations.contains(Constants.ANNOTATION_NAME)) {
        //     return true
        // }
        return true
    }
}
