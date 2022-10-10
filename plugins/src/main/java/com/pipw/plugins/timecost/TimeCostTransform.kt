package com.pipw.plugins.timecost

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor

/**
 * Author:zhaenpg
 * Date:2022/10/9
 * Description:
 *  AsmClassVisitorFactory是Android 官方提供的用于代替Transform的类？（Transform 在gradle 7.0后被标为废弃）
 */
abstract class TimeCostTransform : AsmClassVisitorFactory<InstrumentationParameters.None> {


    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        return TimeCostClassVisitor(nextClassVisitor,classContext.currentClassData.className)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return classData.className.contains("com.pipw") //类名包含目标名是才进行代码插桩
    }
}