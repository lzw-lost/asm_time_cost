package com.upuphone.asm

import com.upuphone.asm.util.Constants
import com.upuphone.asm.util.Constants.Companion.TimeCache
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * author : lzw
 * date   : 2023-2-9 19:55
 */
class TimeCostMethodVisitor(
    api: Int, methodVisitor: MethodVisitor?, access: Int,
    private val methodName: String?, descriptor: String?,
    private val className: String, private var logAll: Boolean,
    private var tagName: String?
) : AdviceAdapter(api, methodVisitor, access, methodName, descriptor) {
    
    private var startVar: Int = 0
    
    @Override
    override fun onMethodEnter() {
        // 方法开始
        if (isNeedVisitMethod(methodName) && logAll) {
            // && !logMethod
            startVar = newLocal(Type.LONG_TYPE)
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
            mv.visitIntInsn(LSTORE, startVar)
        }
        super.onMethodEnter()
    }
    
    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        if (descriptor.equals(Constants.ANNOTATION_NAME)) {
            logAll = true
        }
        return DurAnnotationVisitor(super.visitAnnotation(descriptor, visible))
    }
    
    @Override
    override fun onMethodExit(opcode: Int) {
        // 方法结束
        if (isNeedVisitMethod(methodName) && logAll) {
            mv.visitVarInsn(LLOAD, startVar)
            mv.visitLdcInsn(tagName)
            mv.visitLdcInsn(className)
            mv.visitLdcInsn(methodName)
            
            mv.visitMethodInsn(
                INVOKESTATIC, TimeCache, "getTimeLog",
                "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false
            )
        }
        super.onMethodExit(opcode)
    }
    
    private fun isNeedVisitMethod(name: String?): Boolean {
        return name != "getTimeLog" && name != "putEndTime" && name != "<clinit>" && name != "printlnTime" && name != "<init>"
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