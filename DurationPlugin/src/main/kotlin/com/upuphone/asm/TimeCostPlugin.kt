package com.upuphone.asm

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class TimeCostPlugin : Plugin<Project> {
    companion object {
        var list = listOf<String>()
    }
    
    override fun apply(project: Project) {
        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        //获取可以通过的包名集合
        val strList: String = project.properties["duration_filter"] as String
        list = strList.split("/")
        println("##########$list")
        androidComponents.onVariants { variant ->
            variant.instrumentation.transformClassesWith(
                TimeCostTransform::class.java,
                InstrumentationScope.ALL
            ) {}
            variant.instrumentation.setAsmFramesComputationMode(
                FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
            )
        }
    }
    
    class MyPluginExtensionsExtensions {
        lateinit var duration_filter: List<String>
    }
}