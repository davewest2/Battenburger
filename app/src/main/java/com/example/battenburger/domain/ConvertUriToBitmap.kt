package com.example.battenburger.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.battenburger.TAG
import java.io.File

fun convertUriToBitmap(context: Context): Bitmap {
    val image = File(context.filesDir, "image.jpg")
    val selectedImageBitmap = BitmapFactory.decodeFile(image.absolutePath)
    Log.d(TAG, "Uri converted to bitmap")
    return selectedImageBitmap
}
