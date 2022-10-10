package com.pipw.androidgradle

import android.util.Log

/**
 * Author:zhaenpg
 * Date:2022/10/10
 * Description:
 */
object TimeManager {

    private const val TAG = "TimeManager"
    private val hashMap = mutableMapOf<String, Long>()

    @JvmStatic
    private var start = 0L

    @JvmStatic
    fun start(method: String, clz: String) {
        start = System.currentTimeMillis()
        hashMap["${clz}-${method}"] = start

    }

    @JvmStatic
    fun end(method: String, clz: String) {
        val start = hashMap["${clz}-${method}"]
        if (start == null) {
            Log.e(TAG, "Class : $clz , method : $method ,has Not record start time")
            return
        }
        val count = System.currentTimeMillis() - start
        Log.d(TAG, "Class : $clz , Method : ${method} , cost time = $count")
    }
}