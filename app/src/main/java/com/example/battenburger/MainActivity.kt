package com.example.battenburger

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.battenburger.domain.CropImageScreenViewModel
import com.example.battenburger.ui.theme.BattenburgerTheme

// TODO: Asynchronous processing of images to move away from reliance on buttons to control processes
// TODO: Implement viewmodels for all screens
// TODO: Improve UI and Battenburg image so that it more resembles a cake slice
// TODO: Add function to save Battenburg image
// TODO: If possible to find a workable cropimage solution, would it be possible to crop to battenburg outline. Will make marzipan more tricky. 
// TODO: If cropimage function still unworkable, explore Canvas options and try to create slice from composable. Canvas layers - like the frames for cat effects etc? 

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

