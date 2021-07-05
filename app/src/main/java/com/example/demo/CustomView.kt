package com.example.demo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

/**
 * Created by tanqing.cc
 * on 2021/7/4
 */

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var firstColor: Int = 0
    private var secondColor: Int = 0
    private var speed: Int = 0
    var mProgress: Int = 0
    var isNext: Boolean = false


    private val mPaint: Paint = Paint()

    init {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.CustomView)
            firstColor = a.getColor(R.styleable.CustomView_firstColor, Color.RED)
            secondColor = a.getColor(R.styleable.CustomView_secondColor, Color.GREEN)
            speed = a.getColor(R.styleable.CustomView_speed, 20)

            Log.d("quincy","${firstColor}  ${secondColor} ${speed}")
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("quincy","onDraw")
        mPaint.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        val len = width * mProgress / 100
        Log.d("quincy","len: $len")

        if (isNext) {
            mPaint.color = firstColor
            canvas?.drawRect(Rect(0, 0, len, height), mPaint)
            mPaint.color = secondColor
            canvas?.drawRect(Rect(len, 0, width, height), mPaint)
        } else {
            mPaint.color = secondColor
            canvas?.drawRect(Rect(0, 0, len, height), mPaint)
            mPaint.color = firstColor
            canvas?.drawRect(Rect(len, 0, width, height), mPaint)
        }
    }

}