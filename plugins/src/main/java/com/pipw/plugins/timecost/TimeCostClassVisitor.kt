package com.pipw.plugins.timecost

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

/**
 * Author:zhaenpg
 * Date:2022/10/9
 * Description:
 */
class TimeCostClassVisitor(nextVisitor: ClassVisitor, private val className: String) :
    ClassVisitor(Opcodes.ASM5, nextVisitor) {

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        val newMethodVisitor =
            object : AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, descriptor) {

                @Override
                override fun onMethodEnter() {
                    // 方法开始
                    if (isNeedVisiMethod(name)) {
                        mv.visitLdcInsn(name)
                        mv.visitLdcInsn(className)
                        methodVisitor.visitMethodInsn(
                            INVOKESTATIC,
                            "com/pipw/androidgradle/TimeManager",
                            "start",
                            "(Ljava/lang/String;Ljava/lang/String;)V",
                            false
                        )
                    }
                    super.onMethodEnter();
                }

                @Override
                override fun onMethodExit(opcode: Int) {
                    // 方法结束
                    if (isNeedVisiMethod(name)) {
                        mv.visitLdcInsn(name);
                        mv.visitLdcInsn(className)
                        methodVisitor.visitMethodInsn(
                            INVOKESTATIC,
                            "com/pipw/androidgradle/TimeManager",
                            "end",
                            "(Ljava/lang/String;Ljava/lang/String;)V",
                            false
                        )
                    }
                    super.onMethodExit(opcode);
                }
            }
        return newMethodVisitor
    }

    //输出的工具类，过滤掉，不然会循环调用
    //可以考虑通过扩展的方式制定
    private val owner = "com.pipw.androidgradle.TimeManager"

    private fun isNeedVisiMethod(name: String?): Boolean {
        if (className == owner) return false
        return name != "<clinit>" && name != "<init>"
    }
}