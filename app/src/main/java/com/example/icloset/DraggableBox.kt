package com.example.icloset

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageView

class DraggableBox @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ImageView(context, attrs, defStyleAttr) {


    private var startingPointerX: Float = 0.toFloat()
    private var startingPointerY: Float = 0.toFloat()
    private var startingViewX: Float = 0.toFloat()
    private var startingViewY: Float = 0.toFloat()



    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                startingViewX = x
                startingViewX = y
                startingPointerX = event.rawX
                startingPointerY = event.rawY


            }
            MotionEvent.ACTION_MOVE -> {
                var pointerX = event.rawX
                var pointerY = event.rawY
                var dx = pointerX - startingPointerX
                var dy = pointerY - startingPointerY
                var viewX = startingViewX + dx
                var viewY = startingViewY + dy
                x = viewX
                y = viewY


            }
        }
        return true
    }


}
