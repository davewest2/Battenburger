package com.example.battenburger.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.Screen
import com.example.battenburger.domain.BattenburgImageScreenViewModel
import com.example.battenburger.domain.convertUriToBitmap
import com.example.battenburger.domain.provideQuadimage
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
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            modifier = Modifier.size(50.dp)
        )
            Button(
                elevation = ButtonDefaults.buttonElevation(20.dp,0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta, contentColor = Color.Yellow),
                onClick = {
                    GlobalScope.launch() {
                        quadImageBitMap = provideQuadimage(viewmodel.viewmodelImageToBattenburg)
                    //manualOverlay(context, viewmodel.viewmodelImageToBattenburg)
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

        Spacer(
            modifier = Modifier.size(50.dp)
        )
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