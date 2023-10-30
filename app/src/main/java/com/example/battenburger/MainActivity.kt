package com.example.battenburger

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.battenburger.domain.convertUriToBitmap
import com.example.battenburger.domain.pixelManipulator
import com.example.battenburger.domain.saveImageToInternalStorage
import com.example.battenburger.ui.theme.BattenburgerTheme


const val TAG = "TAG"
lateinit var imageBitMap: Bitmap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ImageSelectionScreen()
            //Navigation()
            BattenburgerTheme {
                Log.d(TAG, "starting app")
                val stockBitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
                val context = LocalContext.current
                val files: Array<String> = context.fileList()
                var selectedImageUri by remember {
                    mutableStateOf<Uri?>(null)
                }
                var quadBitMapToDisplay by remember {
                    mutableStateOf<Bitmap>(stockBitmap)
                }

                val photoPickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { contentUri -> selectedImageUri = contentUri }
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
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(onClick = {
                                saveImageToInternalStorage(context, selectedImageUri!!)
                                Log.d(TAG, "image saved to internal storage, filename ${files[8]} which should be image.jpg")
                            }
                            ) {
                                Text(text = "Save image")
                            }
                            Button(onClick = {
                                imageBitMap = convertUriToBitmap(context)
                                Log.d(TAG, "uri converted to bitmap")
                            }
                            ) {
                                Text(text = "Convert image to bitmap")
                            }

                            Button(onClick = {
                                quadBitMapToDisplay = pixelManipulator(imageBitMap)
                                Log.d(TAG, "Join images button pressed")
                            }
                            ) {
                                Text(text = "Battenburg image")
                            }
                        }

                        AsyncImage(
                            model = quadBitMapToDisplay,
                            contentDescription = "my photo battenburged",
                            modifier = Modifier.fillMaxSize(),
                        )
                        Log.d(TAG, "Async quadbitmap image displayed")
                        Log.d(TAG, "Size of quadbitmap image is ${quadBitMapToDisplay.width} wide and ${quadBitMapToDisplay.height} high")

                    }
                }


            }
        }
    }
}

