package com.pipw.androidgradle

/**
 * Author:zhaenpg
 * Date:2022/10/10
 * Description:
 */
class MethodTimeCount {

    fun count() {
        TimeManager.start("MethodTimeCount", "count")

        TimeManager.end("MethodTimeCount", "count")
    }
}