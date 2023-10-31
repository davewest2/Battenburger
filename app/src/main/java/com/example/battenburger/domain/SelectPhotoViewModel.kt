package com.example.battenburger.domain

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SelectPhotoViewModel: ViewModel() {

    val stockBitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
    var selectedImageUri by mutableStateOf<Uri?>(Uri.parse("android.resource://com.example.battenburger/drawable/dave_summer_cross_r2_14_june_2023.bmp"))
    var quadBitMapToDisplay by mutableStateOf<Bitmap>(stockBitmap)

}