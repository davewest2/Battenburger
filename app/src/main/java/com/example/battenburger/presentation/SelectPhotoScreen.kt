package com.example.battenburger.presentation

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.Screen
import com.example.battenburger.TAG
import com.example.battenburger.domain.SelectPhotoViewModel
import com.example.battenburger.domain.saveImageToInternalStorage
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
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    photoPickerLauncher.launch("image/*")
                }
                ) {
                    Text(text = "Pick a photo")
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier.weight(0.33f),
                    onClick = {
                        GlobalScope.launch(Dispatchers.IO) {
                            saveImageToInternalStorage(context, selectPhotoViewModel.selectedImageUri!!)
                            Log.d(TAG, "image saved to internal storage, filename ${files} which should be image.jpg")
                        }
                        navController.navigate(Screen.CropImageScreen.route)
                    }

                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Save image")
                }
            }

            AsyncImage(
                model = selectPhotoViewModel.selectedImageUri,
                contentDescription = "my selected image",
                modifier = Modifier.fillMaxSize(),
            )
            Log.d(TAG, "Async selected image displayed")
        }
    }
}