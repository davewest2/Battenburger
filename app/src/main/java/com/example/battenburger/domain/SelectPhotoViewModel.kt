package com.example.battenburger.domain

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel

class SelectPhotoViewModel: ViewModel() {

    var selectedImageUri by mutableStateOf<Uri?>(null)




}