package com.pipw.androidgradle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        count()

        findViewById<Button>(R.id.btn1).setOnClickListener {
            Log.d(TAG, "onCreate: button1 click")
        }

        findViewById<Button>(R.id.btn2).setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick: button2 click")
            }
        })
    }

    fun count(){
        Thread.sleep(10L)
    }

}