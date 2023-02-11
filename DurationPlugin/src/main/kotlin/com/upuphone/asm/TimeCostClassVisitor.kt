package com.upuphone.asm

import com.upuphone.asm.util.Constants.Companion.ANNOTATION_NAME
import com.upuphone.asm.util.LogHelper
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class TimeCostClassVisitor(nextVisitor: ClassVisitor, private val className: String) : ClassVisitor(Opcodes.ASM5, nextVisitor) {
    private var logAll: Boolean = false
    private var tagName: String? = null
    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
      
        if (descriptor.equals(ANNOTATION_NAME)) {
            logAll = true
        }
        // LogHelper.log("======$descriptor $className ======")
        return DurAnnotationVisitor(super.visitAnnotation(descriptor, visible))
    }
    
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        return TimeCostMethodVisitor(Opcodes.ASM5, methodVisitor, access, name, descriptor, className, logAll, tagName)
    }
    
    inner class DurAnnotationVisitor(annotationVisitor: AnnotationVisitor) : AnnotationVisitor(Opcodes.ASM5, annotationVisitor) {
        
        override fun visit(name: String?, value: Any?) {
            when (name) {
                "tag" ->
                    tagName = value as String?
            }
            super.visit(name, value)
        }
    }
}