package com.pipw.plugins.timecost

import com.android.build.api.extension.AndroidComponentsExtension
import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author:zhaenpg
 * Date:2022/10/9
 * Description:
 *  方法插桩耗时统计输出
 */
class TimeCostPlugin : Plugin<Project> {

    override fun apply(p0: Project) {
        //注册插件
        val androidComponents = p0.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.transformClassesWith(
                TimeCostTransform::class.java,
                InstrumentationScope.PROJECT //InstrumentationScope控制是否需要扫描依赖库代码
            ) {}
            variant.setAsmFramesComputationMode(
                FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
            )
        }
    }
}