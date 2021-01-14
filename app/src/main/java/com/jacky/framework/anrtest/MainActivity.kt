package com.jacky.framework.anrtest

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.measureTimeMillis

const val tag = "abm"

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.tv).setOnClickListener {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            Toast.makeText(this@MainActivity, "123", Toast.LENGTH_SHORT).show()
        }
        val editText = findViewById<EditText>(R.id.edit_text)
        editText.setOnTouchListener { v, event ->
            Log.i(tag, "event=" + event.action)
            if (event.action == MotionEvent.ACTION_DOWN) {
                try {
                    Thread.sleep(50000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            false
        }

        // hook ActivityManager
        val activityManagerClass = ActivityManager::class.java
        try {
            val getService = activityManagerClass.getDeclaredMethod("getService")
            getService.isAccessible = true
            val iActivityManager = getService.invoke(null)
            val getRunningServices = iActivityManager.javaClass.getMethod("getServices", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            val services = getRunningServices.invoke(iActivityManager, 1, 0)
            if (services != null) {
                Log.d(tag, services.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
//        Thread.sleep(Long.MAX_VALUE)
//        val currentTimeMillis = System.currentTimeMillis() //记录系统当前时间
//        val measureTimeMillis = measureTimeMillis {
//            while (true) { //此处循环便是模拟的耗时操作，不断获取时间10s之后结束。
//                val timeMillis = System.currentTimeMillis()
//                if (timeMillis - currentTimeMillis >= 10000) {
//                    Log.d(tag, "onStart return time is: " + (timeMillis - currentTimeMillis))
//                    break
//                }
//            }
//        }
//        Log.d(tag, "start cost $measureTimeMillis ms")
    }
}