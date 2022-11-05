package com.pipw.androidgradle

import android.util.Log

/**
 * Author:zhaenpg
 * Date:2022/10/13
 * Description:
 */
object ClickFastFilter {

    private const val TAG = "ClickFastFilter"

    @JvmStatic
    private var lastClickTime = 0L

    @JvmStatic
    var fastClickSpaceTime = 500L

    @JvmStatic
    fun filter(): Boolean {
        val cur = System.currentTimeMillis()
        if (lastClickTime == 0L || cur - lastClickTime > fastClickSpaceTime) {
            Log.d(TAG, "filter:not fast click")
            return false
        }
        Log.d(TAG, "filter: fast click")
        lastClickTime = cur
        return true
    }
}