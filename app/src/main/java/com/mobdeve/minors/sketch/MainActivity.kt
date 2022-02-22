package com.mobdeve.minors.sketch

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import com.mobdeve.minors.sketch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var windowWidth = 0f
    private var windowHeight = 0f

    private var bitmap : Bitmap? = null
    private var canvas : Canvas? = null
    private var paint : Paint? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Get the width/height of our window/screen
        val currentDisplay = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(currentDisplay)
        windowHeight = (currentDisplay.heightPixels * 2).toFloat()
        windowWidth = (currentDisplay.widthPixels * 2).toFloat()

        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val display2 = windowManager.defaultDisplay
        val outPoint= Point()

        display2.getSize(outPoint)

        var width = 0
        var height = 0

        if (outPoint.y > outPoint.x){
            height = outPoint.y
            width = outPoint.x
        } else {
            height = outPoint.x
            width = outPoint.y
        }

        // Loads an image from your device and you can draw on that image
        bitmap = Bitmap.createBitmap(
            width, height, Bitmap.Config.ARGB_8888
        )

        canvas = Canvas(bitmap!!)
        paint = Paint()
    }
}