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
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
suspend fun pixelManipulator(context: Context, bitmap: Bitmap): Bitmap {
    val resources = context.resources
    val exampleBitmap = BitmapFactory.decodeResource(resources, R.drawable.daveandkatie)
    // TODO: Change back to bitmap variable when the selected photo is required
    val ratio1 = exampleBitmap.width.toDouble()
    val ratio2 = exampleBitmap.height.toDouble()
    val ratio = ((ratio1/ratio2)*500).toInt()
    Log.d(TAG, "ratio1 is $ratio1 ratio 2 is $ratio2 ratio is $ratio")
    val battenburgQuarterImageBitmap = Bitmap.createScaledBitmap(exampleBitmap, ratio, 500,true)
    val imageHeight = battenburgQuarterImageBitmap.height
    val imageWidth = battenburgQuarterImageBitmap.width
    Log.d(TAG, "bitmap height is ${battenburgQuarterImageBitmap.height} and width is ${battenburgQuarterImageBitmap.width}")
    val emptyQuadBitmap = Bitmap.createBitmap(imageWidth *2, imageHeight *2, Bitmap.Config.ARGB_8888)

    val battenburgSliceImage = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice2)
    var resizedBattenburgBackgroundImage = Bitmap.createScaledBitmap(battenburgSliceImage, (emptyQuadBitmap.width*1.5).toInt(), (emptyQuadBitmap.height*1.25).toInt(),true)
    GlobalScope.launch {
        Log.d(TAG, "Joinimages function called, GlobalScope coroutine launched")
Log.d(TAG, "Katie drawable is of size ${battenburgSliceImage.width} width and ${battenburgSliceImage.height} height")

// TODO: Get a squarer cake image and/or do the overlay so that the image is contained within the coloured quarter
// TODO: sort out the resizing function so that either a) the orginal aspect ratio is retained or b) the image is cropped to that ratio
// TODO: Eliminated the pinkpixel and yellowpixel changes as the battenburg slice colour effects this colour change

        val overlayJustSquaresx1 = (0.18*resizedBattenburgBackgroundImage.width).toInt()
        val overlayJustSquaresx2 = (0.82*resizedBattenburgBackgroundImage.width).toInt()
        val overlayJustSquaresy1 = (0.06*resizedBattenburgBackgroundImage.height).toInt()
        val overlayJustSquaresy2 = (0.72*resizedBattenburgBackgroundImage.height).toInt()

        for (i in 0 until battenburgQuarterImageBitmap.width) {
            for (j in 0 until battenburgQuarterImageBitmap.height) {
                val pixel = battenburgQuarterImageBitmap.getPixel(i, j)
                val red = android.graphics.Color.red(pixel)
                val green = android.graphics.Color.green(pixel)
                val blue = android.graphics.Color.blue(pixel)
                val pinkPixel = Color(minOf(255, red+50), maxOf(0, green-30), maxOf(0, blue-30)).toArgb()
                val yellowPixel = Color(minOf(255, red+50), minOf(255, green+50), maxOf(0, blue-20)).toArgb()

                emptyQuadBitmap.setPixel(i, j, pixel)
                emptyQuadBitmap.setPixel(i+battenburgQuarterImageBitmap.width, j, pixel)
                emptyQuadBitmap.setPixel(i, j+battenburgQuarterImageBitmap.height, pixel)
                emptyQuadBitmap.setPixel(i+battenburgQuarterImageBitmap.width, j+battenburgQuarterImageBitmap.height, pixel)
            }
        }

        Log.d(TAG, "Quadbitmap complete size is ${emptyQuadBitmap.width} wide ${emptyQuadBitmap.height} height")

        val overlayWidth = emptyQuadBitmap.width
        val overlayHeight = emptyQuadBitmap.height

            for (i in 0 until overlayWidth){
            for (j in 0 until overlayHeight) {
                val maskPixel = resizedBattenburgBackgroundImage.getPixel(i+overlayJustSquaresx1, j+overlayJustSquaresy1)
                val maskRed = android.graphics.Color.red(maskPixel)
                val maskGreen = android.graphics.Color.green(maskPixel)
                val maskBlue = android.graphics.Color.blue(maskPixel)
                val quadBitmapPixel = emptyQuadBitmap.getPixel(i,j)
                val quadRed = android.graphics.Color.red(quadBitmapPixel)
                val quadGreen = android.graphics.Color.green(quadBitmapPixel)
                val quadBlue = android.graphics.Color.blue(quadBitmapPixel)
                val overlayRed = ((maskRed*0.65)+(quadRed*0.35)).toInt()
                val overlayGreen = ((maskGreen*0.65)+(quadGreen*0.35)).toInt()
                val overlayBlue = ((maskBlue*0.65)+(quadBlue*0.35)).toInt()
                val overlayPixel = Color(overlayRed, overlayGreen, overlayBlue).toArgb()

                resizedBattenburgBackgroundImage.setPixel((i+overlayJustSquaresx1),(j+overlayJustSquaresy1),overlayPixel)
// TODO: Blend the edge of the overlaid image. Gradually change the overlay values for some pixels at the edges. 
            }
        }
        //marzipanThis(quadBitmap)


    }
    return resizedBattenburgBackgroundImage
}

private fun marzipanThis(quadBitmap: Bitmap) {
    val marzipan = Color(255,255,0).toArgb()
    for (i in 0 until quadBitmap.width) {
        for (j in 0..20) {
            quadBitmap.setPixel(i, j, marzipan)
        }
    }

    for (i in 0 until quadBitmap.width) {
        for (j in quadBitmap.height - 20 until quadBitmap.height) {
            quadBitmap.setPixel(i, j, marzipan)
        }
    }

    for (i in 0 until 20) {
        for (j in 0 until quadBitmap.height) {
            quadBitmap.setPixel(i, j, marzipan)
        }
    }

    for (i in quadBitmap.width - 20 until quadBitmap.width - 1) {
        for (j in 0 until quadBitmap.height) {
            quadBitmap.setPixel(i, j, marzipan)
        }
    }
}
