package com.example.battenburger.domain

import android.graphics.Bitmap


fun provideSquareImageForQuad(bitmap: Bitmap): Bitmap {
    val quarterBitmapWidth = bitmap.width.toDouble()
    val quarterBitmapHeight = bitmap.height.toDouble()
    var startX = 0
    var endX = 0
    var startY = 0
    var endY = 0

    if (quarterBitmapWidth >= quarterBitmapHeight) {
        val difference = quarterBitmapWidth - quarterBitmapHeight
        startX = (difference/2).toInt()
        endX = (quarterBitmapHeight - startX).toInt()
        endY = (quarterBitmapHeight -1).toInt()
    } else {
        val difference = quarterBitmapHeight - quarterBitmapWidth
        endX = (quarterBitmapWidth-1).toInt()
        startY = (difference/2).toInt()
        endY = (quarterBitmapHeight-startY).toInt()
    }
    val ratio = minOf(quarterBitmapWidth,quarterBitmapHeight).toInt()
    val quadImageBitmapHolder = Bitmap.createBitmap(ratio, ratio, Bitmap.Config.ARGB_8888)
    val imageHeight = quadImageBitmapHolder.height
    val imageWidth = quadImageBitmapHolder.width

    for (i in startX until endX) {
        for (j in startY until endY) {
            val pixel = bitmap.getPixel(i, j)
            quadImageBitmapHolder.setPixel(i-startX, j - startY, pixel )
        }
    }
    return createQuadImageBitmap(imageWidth, imageHeight, quadImageBitmapHolder)
}

private fun createQuadImageBitmap(
    imageWidth: Int,
    imageHeight: Int,
    quadImageBitmap: Bitmap
): Bitmap {
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
    return Bitmap.createScaledBitmap(quadBitmapHolder, 750, 750, true)
}

