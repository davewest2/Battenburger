package com.example.battenburger.domain

import android.graphics.Bitmap


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







