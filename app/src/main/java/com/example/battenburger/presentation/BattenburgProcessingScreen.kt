package com.example.battenburger.presentation

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
import com.example.battenburger.domain.BattenburgImageScreenViewModel
import com.example.battenburger.domain.convertUriToBitmap
import com.example.battenburger.domain.manualOverlay
import com.example.battenburger.quadImageBitMap
import com.example.battenburger.selectedImageBitMap
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun BattenburgProcessingScreen(navController: NavController){
    val context = LocalContext.current
    selectedImageBitMap = convertUriToBitmap(context)
    val viewmodel = BattenburgImageScreenViewModel()

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
                        quadImageBitMap = manualOverlay(context, viewmodel.viewmodelImageToBattenburg)
                    }
                    runBlocking {
                        delay(3000L)
                        navController.navigate(Screen.DisplayBattenburgScreen.route)
                    }
                }
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Battenburg it!")
            }
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