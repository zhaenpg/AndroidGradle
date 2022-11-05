package com.pipw.androidgradle

import android.view.View

/**
 * Author:zhaenpg
 * Date:2022/10/13
 * Description:
 */
class ClickFilterUse {


    fun test(view: View) {
        view.setOnClickListener{
            if (ClickFastFilter.filter()){
                return@setOnClickListener
            }
        }
    }
}