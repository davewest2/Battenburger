package com.example.battenburger.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.battenburger.R
import com.example.battenburger.TAG
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun pixelManipulator(context: Context, bitmap: Bitmap): Bitmap {
    val resources = context.resources
    val exampleBitmap = BitmapFactory.decodeResource(resources, R.drawable.daveandkatie)
    // TODO: Change back to bitmap variable when the selected photo is required
    val ratio1 = exampleBitmap.width.toDouble()
    val ratio2 = exampleBitmap.height.toDouble()
    val ratio = ((ratio1/ratio2)*500).toInt()
    Log.d(TAG, "ratio1 is $ratio1 ratio 2 is $ratio2 ratio is $ratio")
    val resizedImageBitmap = Bitmap.createScaledBitmap(exampleBitmap, ratio, 500,true)
    val imageHeight = resizedImageBitmap.height
    val imageWidth = resizedImageBitmap.width
    Log.d(TAG, "bitmap height is ${resizedImageBitmap.height} and width is ${resizedImageBitmap.width}")
    val quadBitmap = Bitmap.createBitmap(imageWidth * 2, imageHeight * 2, Bitmap.Config.ARGB_8888)

    val drawableBitmap = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice)
    val scaledDrawableBitmap = Bitmap.createScaledBitmap(drawableBitmap, ratio*2, 1000,true)
    GlobalScope.launch {
        Log.d(TAG, "Joinimages function called, GlobalScope coroutine launched")
Log.d(TAG, "Katie drawable is of size ${drawableBitmap.width} width and ${drawableBitmap.height} height")

// TODO: Play about more with the overlay settings. Need to create the overlay image from a battenburg cake picture
// TODO: sort out the resizing function so that either a) the orginal aspect ratio is retained or b) the image is cropped to that ratio

        val marzipan = Color(255,255,0).toArgb()
        for (i in 0 until resizedImageBitmap.width) {
            for (j in 0 until resizedImageBitmap.height) {
                val pixel = resizedImageBitmap.getPixel(i, j)
                val red = android.graphics.Color.red(pixel)
                val green = android.graphics.Color.green(pixel)
                val blue = android.graphics.Color.blue(pixel)
                val pinkPixel = Color(minOf(255, red+50), maxOf(0, green-30), maxOf(0, blue-30)).toArgb()
                val yellowPixel = Color(minOf(255, red+50), minOf(255, green+50), maxOf(0, blue-20)).toArgb()

                quadBitmap.setPixel(i, j, pinkPixel)
                quadBitmap.setPixel(i+resizedImageBitmap.width, j, yellowPixel)
                quadBitmap.setPixel(i, j+resizedImageBitmap.height, yellowPixel)
                quadBitmap.setPixel(i+resizedImageBitmap.width, j+resizedImageBitmap.height, pinkPixel)
            }
        }

        Log.d(TAG, "Quadbitmap complete size is ${quadBitmap.width} wide ${quadBitmap.height} height")

        val overlayWidth = minOf(quadBitmap.width, scaledDrawableBitmap.width)
        val overlayHeight = minOf(quadBitmap.height, scaledDrawableBitmap.height)

        for (i in 0 until overlayWidth){
            for (j in 0 until overlayHeight) {
                val maskPixel = scaledDrawableBitmap.getPixel(i, j)
                val maskRed = android.graphics.Color.red(maskPixel)
                val maskGreen = android.graphics.Color.green(maskPixel)
                val maskBlue = android.graphics.Color.blue(maskPixel)
                val quadBitmapPixel = quadBitmap.getPixel(i,j)
                val quadRed = android.graphics.Color.red(quadBitmapPixel)
                val quadGreen = android.graphics.Color.green(quadBitmapPixel)
                val quadBlue = android.graphics.Color.blue(quadBitmapPixel)
                val overlayRed = ((maskRed*0.5)+(quadRed*0.5)).toInt()
                val overlayGreen = ((maskGreen*0.5)+(quadGreen*0.5)).toInt()
                val overlayBlue = ((maskBlue*0.5)+(quadBlue*0.5)).toInt()
                val overlayPixel = Color(overlayRed, overlayGreen, overlayBlue).toArgb()

                quadBitmap.setPixel(i,j,overlayPixel)

            }
        }
        for (i in 0 until quadBitmap.width) {
            for (j in 0..20) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }

        for (i in 0 until quadBitmap.width) {
            for (j in quadBitmap.height-20 until quadBitmap.height) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }

        for (i in 0 until 20) {
            for (j in 0 until quadBitmap.height) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }

        for (i in quadBitmap.width-20 until quadBitmap.width-1) {
            for (j in 0 until quadBitmap.height) {
                quadBitmap.setPixel(i,j,marzipan)
            }
        }


    }
    return quadBitmap
}
