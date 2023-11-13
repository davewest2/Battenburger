package com.example.battenburger.presentation

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.Screen
import com.example.battenburger.TAG
import com.example.battenburger.domain.convertUriToBitmap
import com.example.battenburger.domain.pixelManipulator
import com.example.battenburger.quadImageBitMap
import com.example.battenburger.selectedImageBitMap
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun BattenburgImageScreen(navController: NavController){
    val context = LocalContext.current
    selectedImageBitMap = convertUriToBitmap(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            Button(
                modifier = Modifier.weight(0.33f),
                onClick = {
                    GlobalScope.launch() {
                        quadImageBitMap = pixelManipulator(context, selectedImageBitMap)
                        Log.d(TAG, "pixelmanipulator called, creating burgimage....long process started")
                        Log.d(TAG, "quadbitmap set to burgimage from above long process")
                    }
                    runBlocking {
                        delay(3000L)
                        navController.navigate(Screen.DisplayBattenburgScreen.route)
                        Log.d(TAG, "navigate to the displaybattenburg screen. If you see this before quadbitmap set to burgimage then we're in trouble!")
                    }
                }
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Battenburg it!")
            }
//            Button(
//                modifier = Modifier.weight(0.33f),
//                onClick = {
//                    navController.navigate(Screen.DisplayBattenburgScreen.route)
//                    Log.d(TAG, "Join images button pressed")
//                }
//            ) {
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = "View Battenburger!")
//            }
        }

        AsyncImage(
            model = selectedImageBitMap,
            contentDescription = "my selected image as a bitmap",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(0.8f),
            alignment = Alignment.TopCenter,

        )
    }
}