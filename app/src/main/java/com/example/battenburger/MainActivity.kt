package com.example.battenburger

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.battenburger.ui.theme.BattenburgerTheme

// TODO: If possible to find a workable cropimage solution, would it be possible to crop to battenburg outline. Will make marzipan more tricky. 
// TODO: If cropimage function still unworkable, explore Canvas options and try to create slice from composable. Canvas layers - like the frames for cat effects etc? 

const val TAG = "TAG"
lateinit var selectedImageBitMap: Bitmap
lateinit var quadImageBitMap: Bitmap
lateinit var saveIt: Bitmap

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

