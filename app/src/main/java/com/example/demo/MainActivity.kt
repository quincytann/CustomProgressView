package com.example.demo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            test.mProgress++
            if (test.mProgress >= 100) {
                test.mProgress = 0
                test.isNext = !test.isNext
            }
            test.postInvalidate()
            try {
                Thread.sleep(20L)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            handler.postDelayed(this, 0L)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 0L)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}