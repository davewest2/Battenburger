package com.example.battenburger.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.battenburger.domain.UseCanvasToOverlay
import com.example.battenburger.selectedImageBitMap

//  API level < 29 (Android 10): WRITE_EXTERNAL_STORAGE required

@Composable
fun DisplayBattenburgScreen(navController: NavController){

    val context = LocalContext.current
    UseCanvasToOverlay(bitmap1 = selectedImageBitMap, context = context)

}





