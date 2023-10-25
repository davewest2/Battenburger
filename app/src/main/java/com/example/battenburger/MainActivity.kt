package com.example.battenburger

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHost
import coil.compose.AsyncImage
import com.example.battenburger.presentation.TransformImage
import com.example.battenburger.ui.theme.BattenburgerTheme
import java.io.File
import java.io.FileOutputStream
import java.util.Date


// TODO: Insert a button to Battenburg Me which goes to the next screen.
// TODO: Next screen takes the image and does all the save and coloration
// TODO: Need to work out if the save stuff needs to be done asynchronously and then how to use the saved version
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
                val context = LocalContext.current
                var files: Array<String> = context.fileList()
                var selectedImageUri by remember {
                    mutableStateOf<Uri?>(null)
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
                                Text(text = "Seve image")
                            }
                        }
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = "my photo",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Log.d(TAG, "Async image displayed")

                        DisplaySavedImage()

                    }
                }


            }
        }
    }
}

