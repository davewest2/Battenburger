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
        val marzipan = Color(255,255,0).toArgb()
        for (i in 0 until bitmap.width) {
            for (j in 0 until imageHeight) {
                val pixel = bitmap.getPixel(i, j)
                val red = android.graphics.Color.red(pixel)
                val green = android.graphics.Color.green(pixel)
                val blue = android.graphics.Color.blue(pixel)
                val pinkPixel = Color(red+50, green-20, blue-20).toArgb()
                val yellowPixel = Color(red+45, green+45, blue-30).toArgb()


                quadBitmap.setPixel(i, j, pinkPixel)
                quadBitmap.setPixel(i+bitmap.width, j, yellowPixel)
                quadBitmap.setPixel(i, j+bitmap.height, yellowPixel)
                quadBitmap.setPixel(i+bitmap.width, j+bitmap.height, pinkPixel)
            }
        }
        for (i in 0 until quadBitmap.width) {
            for (j in 0..80) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }

        for (i in 0 until quadBitmap.width) {
            for (j in quadBitmap.height-80 until quadBitmap.height) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }

        for (i in 0 until 80) {
            for (j in 0 until quadBitmap.height) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }

        for (i in quadBitmap.width-80 until quadBitmap.width-1) {
            for (j in 0 until quadBitmap.height) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }
        Log.d(TAG, "Quadbitmap complete")

    }
    return quadBitmap
}
