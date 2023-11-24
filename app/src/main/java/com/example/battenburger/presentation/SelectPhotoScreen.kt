package com.example.battenburger.presentation

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.R
import com.example.battenburger.Screen
import com.example.battenburger.TAG
import com.example.battenburger.domain.SelectPhotoViewModel
import com.example.battenburger.domain.saveImageToInternalAppStorage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SelectPhotoScreen(navController: NavController) {

    val selectPhotoViewModel = SelectPhotoViewModel()
    val context = LocalContext.current
    val files: Array<String> = context.fileList()
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { contentUri -> selectPhotoViewModel.selectedImageUri = contentUri }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow),
                horizontalArrangement = Arrangement.SpaceAround,

                ) {
                Button(onClick = {
                    photoPickerLauncher.launch("image/*")
                },
                    elevation = ButtonDefaults.buttonElevation(20.dp,0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta, contentColor = Color.Yellow)
                ) {
                    Text(text = "Pick a photo")
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom

            ) {
                Button(
                    onClick = {
                        GlobalScope.launch(Dispatchers.IO) {
                            saveImageToInternalAppStorage(context, selectPhotoViewModel.selectedImageUri!!)
                            Log.d(TAG, "image saved to internal storage, filename ${files} which should be image.jpg")
                        }
                        navController.navigate(Screen.BattenburgProcessingScreen.route)
                    },
                    elevation = ButtonDefaults.buttonElevation(20.dp,0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta, contentColor = Color.Yellow)

                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Save image")
                }
            }
            AsyncImage(
                model = selectPhotoViewModel.selectedImageUri,
                contentDescription = "my selected image",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow),
                placeholder = painterResource(
                    id = R.drawable.katie_summer_cross_r2_14_june_2023)

            )
            Log.d(TAG, "Async selected image displayed")
        }
    }
}