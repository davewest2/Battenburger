package com.example.battenburger

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.battenburger.domain.CropImageScreenViewModel
import com.example.battenburger.ui.theme.BattenburgerTheme


const val TAG = "TAG"
lateinit var selectedImageBitMap: Bitmap
lateinit var quadImageBitMap: Bitmap

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattenburgerTheme {
                Navigation()
                Log.d(TAG, "starting Navigation")
            }
        }
    }
}

