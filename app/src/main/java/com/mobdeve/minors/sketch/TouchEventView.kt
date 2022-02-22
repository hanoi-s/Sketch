package com.mobdeve.minors.sketch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView

// Creating our own Image View
class TouchEventView(context: Context, attrs:AttributeSet): AppCompatImageView(context, attrs){

    companion object{               // Companion object for private class GestureListener to recognize
        private val path = Path()
        private var localContext:Context? = null
    }
    private val paint = Paint()     // Pen
    var gestureDetector: GestureDetector? = null

    // Initializes the pen
    // Primarily loads this
    init {
        localContext = context
        gestureDetector =
            GestureDetector(context, GestureListener())

        paint.isAntiAlias = true
        paint.strokeWidth = 6f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    private class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            val x = e.x
            val y = e.y
            path.reset()
            Toast.makeText(localContext,
                "Double Tap >> Tapped at: ($x,$y)",
                Toast.LENGTH_SHORT).show()
            return true
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Locations, where finger is located
        val eventX = event.x
        val eventY = event.y

        // Checks whether finger moved down, up
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(eventX, eventY)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(eventX, eventY)     // When moving, it just points to the next point
            MotionEvent.ACTION_UP -> {}                                // No action coded
            else -> return false                                       // Default gesture
        }
        // Double tap on image view
        gestureDetector!!.onTouchEvent(event)
        invalidate() // The one refreshing / updating the image
        return true
    }


}