package com.example.battenburger.domain

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.battenburger.TAG
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun pixelManipulator(bitmap: Bitmap): Bitmap {
    val imageHeight = bitmap.height
    val imageWidth = bitmap.width
    Log.d(TAG, "bitmap height is ${bitmap.height} and width is ${bitmap.width}")
    val quadBitmap = Bitmap.createBitmap(imageWidth * 2, imageHeight * 2, Bitmap.Config.ARGB_8888)
    GlobalScope.launch {
        Log.d(TAG, "Joinimages function called, GlobalScope coroutine launched")

// TODO: Yellow and pink values not quite achieving the hue I want but shows effect. Need to limit upper and lower values in case >255 or <0

        for (i in 0 until bitmap.width) {
            for (j in 0 until imageHeight) {
                val pixel = bitmap.getPixel(i, j)
                val red = android.graphics.Color.red(pixel)
                val green = android.graphics.Color.green(pixel)
                val blue = android.graphics.Color.blue(pixel)
                val pinkPixel = Color(255, green, blue).toArgb()
                val yellowPixel = Color(red+25, green+25, blue).toArgb()

                quadBitmap.setPixel(i, j, pinkPixel)
                quadBitmap.setPixel(i+bitmap.width, j, yellowPixel)
                quadBitmap.setPixel(i, j+bitmap.height, yellowPixel)
                quadBitmap.setPixel(i+bitmap.width, j+bitmap.height, pinkPixel)
            }
        }
        Log.d(TAG, "Quadbitmap complete")

    }
    return quadBitmap
}
