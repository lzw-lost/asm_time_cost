package com.upuphone.asm

import com.upuphone.asm.util.Constants
import com.upuphone.asm.util.Constants.Companion.TimeCache
import com.upuphone.asm.util.LogHelper
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
    private var tagName: String
) : AdviceAdapter(api, methodVisitor, access, methodName, descriptor) {
    
    private var startVar: Int = 0
    private var endClassName = ""
    private var endMethodName = ""
    
    @Override
    override fun onMethodEnter() {
        // 方法开始
        if (isNeedVisitMethod(methodName) && logAll) {
            if (endClassName.isNotEmpty()) {
                LogHelper.log("===onMethodEnter===   $className ======  $methodName")
                putMethodStart(className, methodName, tagName, endClassName, endMethodName)
                mv.visitLdcInsn(tagName)
                mv.visitLdcInsn(className)
                mv.visitLdcInsn(methodName)
                mv.visitLdcInsn(endClassName)
                mv.visitLdcInsn(endMethodName)
                mv.visitMethodInsn(
                    INVOKESTATIC, TimeCache, "logStart",
                    "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false
                )
            }
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
        // LogHelper.log("====== $className  $logAll  $methodName ======")
        // 方法结束
        if (isNeedVisitMethod(methodName) && logAll) {
            // LogHelper.log("======endClassName= $className $methodName")
            mv.visitVarInsn(LLOAD, startVar)
            mv.visitLdcInsn(tagName)
            mv.visitLdcInsn(className)
            mv.visitLdcInsn(methodName)
            
            mv.visitMethodInsn(
                INVOKESTATIC, TimeCache, "getTimeLog",
                "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false
            )
        }
        val map = getStartTime(className, methodName)
        map?.forEach{ (_, logData) ->
            mv.visitLdcInsn(logData.tag)
            mv.visitLdcInsn(logData.startClassName)
            mv.visitLdcInsn(logData.startMethodName)
            mv.visitLdcInsn(logData.endClassName)
            mv.visitLdcInsn(logData.endMethodName)
            LogHelper.log("===getTimeLogDuration=== ${logData.startClassName}  ${logData.startMethodName}  ${logData.endClassName}  ${logData.endMethodName}======")
            mv.visitMethodInsn(
                INVOKESTATIC, TimeCache, "getTimeLogDuration",
                "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", false
            )
        }
        super.onMethodExit(opcode)
    }
    
    private fun isNeedVisitMethod(name: String?): Boolean {
        return name != "getTimeLogDuration" && name != "logStart" && name != "getTimeLog" && name != "<clinit>" && name != "<init>"
    }
    
    inner class DurAnnotationVisitor(annotationVisitor: AnnotationVisitor) : AnnotationVisitor(Opcodes.ASM5, annotationVisitor) {
        
        override fun visit(name: String?, value: Any?) {
            when (name) {
                "tag" ->
                    tagName = value as String
                
                "endClassName" ->
                    endClassName = value as String
                
                "endMethodName" ->
                    endMethodName = value as String
            }
            super.visit(name, value)
        }
    }
    
    private fun putMethodStart(startClassName: String?, startMethodName: String?, tag: String, endClassName: String?, endMethodName: String?) {
        if (!endClassName.isNullOrEmpty() && !endMethodName.isNullOrEmpty()) {
            val data = LogData()
            data.startClassName = startClassName
            data.startMethodName = startMethodName
            data.tag = tag
            data.endMethodName=endMethodName
            data.endClassName=endClassName
            val key = "$endClassName%$endMethodName"
            var map = LogHelper.methodMap[key]
            if (map == null) {
                map = hashMapOf()
                LogHelper.methodMap[key] = map
            }
            map["$startClassName%$startMethodName"] = data
            
            LogHelper.log("===putMethodStart===   $key ======  ${LogHelper.methodMap}")
        }
    }
    
    private fun getStartTime(className: String?, methodName: String?): HashMap<String, LogData>? {
        // LogHelper.log("===getStartTime===   $className  $methodName ======")
        if (!className.isNullOrEmpty() && !methodName.isNullOrEmpty()) {
            val key = "$className%$methodName"
            val map = LogHelper.methodMap[key]
            LogHelper.log("===getStartTime===   $key ====== ${LogHelper.methodMap}")
            return map
        }
        return null
    }
    class LogData {
        var startClassName: String? = null
        var startMethodName: String? = null
        var endClassName: String? = null
        var endMethodName: String? = null
        var tag: String? = null
    }
  
}