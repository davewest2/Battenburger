package com.example.battenburger.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.*
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.example.battenburger.R
import com.example.battenburger.TAG
import kotlinx.coroutines.DelicateCoroutinesApi


fun provideQuadimage(bitmap: Bitmap): Bitmap {
    val quarterBitmap = Bitmap.createBitmap(bitmap)
    val ratio1 = quarterBitmap.width.toDouble()
    val ratio2 = quarterBitmap.height.toDouble()
    val ratio = ((ratio1 / ratio2) * 500).toInt()
    val quadImageBitmap = Bitmap.createScaledBitmap(quarterBitmap, ratio, ratio, true)
    val imageHeight = quadImageBitmap.height
    val imageWidth = quadImageBitmap.width
    val quadBitmapHolder =
        Bitmap.createBitmap(imageWidth * 2, imageHeight * 2, Bitmap.Config.ARGB_8888)
    //Replicates the user image four times into the emptyQuadBitmap to create the quadImage to overlay on the cake
    for (i in 0 until quadImageBitmap.width) {
        for (j in 0 until quadImageBitmap.height) {
            val pixel = quadImageBitmap.getPixel(i, j)

            quadBitmapHolder.setPixel(i, j, pixel)
            quadBitmapHolder.setPixel(i + quadImageBitmap.width, j, pixel)
            quadBitmapHolder.setPixel(i, j + quadImageBitmap.height, pixel)
            quadBitmapHolder.setPixel(
                i + quadImageBitmap.width,
                j + quadImageBitmap.height,
                pixel
            )
        }
    }
    return quadBitmapHolder
}



fun manualOverlay(context: Context, bitmap: Bitmap): Bitmap {
    val resources = context.resources
    val cakeImage = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice2)
    val resizedCakeImage = Bitmap.createScaledBitmap(cakeImage, (bitmap.width*1.5).toInt(), (bitmap.height*1.25).toInt(),true)

    //X and Y start points located inside the marzipan of the cake image so the quadimage only covers the battenburg squares
    val overlayQuadOnCakeXStart = (0.18*resizedCakeImage.width).toInt()
    val overlayQuadOnCakeYStart = (0.06*resizedCakeImage.height).toInt()

    val overlayWidth = bitmap.width
    val overlayHeight = bitmap.height

    for (i in 0 until overlayWidth){
        for (j in 0 until overlayHeight) {
            val cakePixel = resizedCakeImage.getPixel(i+overlayQuadOnCakeXStart, j+overlayQuadOnCakeYStart)
            val cakeRed = red(cakePixel)
            val cakeGreen = green(cakePixel)
            val cakeBlue = blue(cakePixel)
            val cakeAlpha = cakePixel.alpha
            val quadBitmapPixel = bitmap.getPixel(i,j)
            val quadRed = quadBitmapPixel.red
            val quadGreen = quadBitmapPixel.green
            val quadBlue = quadBitmapPixel.blue
            val quadAlpha = quadBitmapPixel.alpha
            val overlayRed = ((cakeRed*0.65)+(quadRed*0.35)).toInt()
            val overlayGreen = ((cakeGreen*0.65)+(quadGreen*0.35)).toInt()
            val overlayBlue = ((cakeBlue*0.65)+(quadBlue*0.35)).toInt()
            val overlayAlpha = ((cakeAlpha*0.65)+(quadAlpha*0.35)).toInt()
            val overlayPixel = Color(overlayRed, overlayGreen, overlayBlue, overlayAlpha).toArgb()

            resizedCakeImage.setPixel((i+overlayQuadOnCakeXStart),(j+overlayQuadOnCakeYStart),overlayPixel)
        }
    }
    return resizedCakeImage
}

private fun blendThis(quadBitmap: Bitmap, resizedCakeImage: Bitmap) {
//Scan the top 20 rows
    val overlayQuadOnCakeXStart = (0.18*resizedCakeImage.width).toInt()
    val overlayQuadOnCakeYStart = (0.06*resizedCakeImage.height).toInt()
    var q = 0
    for (i in 0 until quadBitmap.width-1) {
        for (j in 0..50) {
            val bottomPixel = resizedCakeImage.getPixel(i+overlayQuadOnCakeXStart, j+overlayQuadOnCakeYStart)
            val topPixels  = quadBitmap.getPixel(i,j)
            val topred = topPixels.red
            val topgreen = topPixels.green
            val topblue = topPixels.blue
            //val topalpha = ((255-(255-j*12))*topPixels.alpha)
            //val topPixelValue =  Color(topred,topgreen,topblue,(255-j*12)).toArgb()
            val bottomred = bottomPixel.red
            val bottomgreen = bottomPixel.green
            val bottomblue = bottomPixel.blue
            val bottomalpha = ((255-j*5)*bottomPixel.alpha)
            val bottomPixelValue = Color(bottomred,bottomgreen,bottomblue,(255-(255-j*5))).toArgb()
            val overlayRed = ((bottomred*(1-(q*0.007)))+(topred*((q*0.007)))).toInt()
            val overlayGreen = ((bottomgreen*(1-(q*0.007)))+(topgreen*((q*0.007)))).toInt()
            val overlayBlue = ((bottomblue*(1-(q*0.007)))+(topblue*((q*0.007)))).toInt()
            //val overlayAlpha = ((bottomalpha*0.65)+(topalpha*0.35)).toInt()
            val overlayPixel = Color(overlayRed, overlayGreen, overlayBlue).toArgb()
            quadBitmap.setPixel(i, j, overlayPixel)
        }
    }
//Scan the bottom 20 rows
    for (i in 0 until quadBitmap.width-1) {
        for (j in quadBitmap.height - 20 until quadBitmap.height-1) {
            val bottomPixels  = quadBitmap.getPixel(i,j)
            val red = bottomPixels.red
            val green = bottomPixels.green
            val blue = bottomPixels.blue
            val bottomPixelValue =  Color(red,green,blue,(quadBitmap.height-j)*12).toArgb()
            quadBitmap.setPixel(i, j, bottomPixelValue)
        }
    }
//Scan the left 20 columns
    for (i in 0 until 20) {
        for (j in 0 until quadBitmap.height-1) {
            val leftPixels  = quadBitmap.getPixel(i,j)
            val red = leftPixels.red
            val green = leftPixels.green
            val blue = leftPixels.blue
            val leftPixelValue =  Color(red,green,blue,(255-(255-q*12))).toArgb()
            quadBitmap.setPixel(i, j, leftPixelValue)
        }
        q++
    }
    q=0
//Scan the right 20 columns
    for (i in quadBitmap.width- 20 until quadBitmap.width- 1) {
        for (j in 0 until quadBitmap.height) {
            val topPixels = quadBitmap.getPixel(i, j)
            val red = topPixels.red
            val green = topPixels.green
            val blue = topPixels.blue
            val setPixelValue = Color(red, green, blue, (255 - q * 12)).toArgb()
            quadBitmap.setPixel(i, j, setPixelValue)
            Log.d(TAG, "x = $i, j = $j ${setPixelValue.alpha}")
        }
        q++
    }
}

