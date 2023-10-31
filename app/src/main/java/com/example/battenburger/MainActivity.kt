package com.example.battenburger

import android.graphics.Bitmap
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.battenburger.domain.SelectPhotoViewModel
import com.example.battenburger.domain.convertUriToBitmap
import com.example.battenburger.domain.pixelManipulator
import com.example.battenburger.domain.saveImageToInternalStorage
import com.example.battenburger.ui.theme.BattenburgerTheme


const val TAG = "TAG"
lateinit var imageBitMap: Bitmap

class MainActivity : ComponentActivity() {

    private val selectPhotoViewModel = SelectPhotoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ImageSelectionScreen()
            //Navigation()
            BattenburgerTheme {
                Log.d(TAG, "starting app")
                //val stockBitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
                val context = LocalContext.current
                val files: Array<String> = context.fileList()
                //var selectedImageUri by remember {
                //    mutableStateOf<Uri?>(null)
                //}
//                var quadBitMapToDisplay by remember {
//                    mutableStateOf<Bitmap>(stockBitmap)
//                }

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
                                saveImageToInternalStorage(context, selectPhotoViewModel.selectedImageUri!!)
                                Log.d(TAG, "image saved to internal storage, filename ${files[8]} which should be image.jpg")
                            }

                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = "Save image")
                            }
                            Button(
                                modifier = Modifier.weight(0.33f),
                                onClick = {
                                imageBitMap = convertUriToBitmap(context)
                                Log.d(TAG, "uri converted to bitmap")
                            }
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = "Convert to bitmap")
                            }

                            Button(
                                modifier = Modifier.weight(0.33f),
                                onClick = {
                                selectPhotoViewModel.quadBitMapToDisplay = pixelManipulator(imageBitMap)
                                Log.d(TAG, "Join images button pressed")
                            }
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = "Battenburg image")
                            }
                        }

                        AsyncImage(
                            model = selectPhotoViewModel.quadBitMapToDisplay,
                            contentDescription = "my photo battenburged",
                            modifier = Modifier.fillMaxSize(),
                        )
                        Log.d(TAG, "Async quadbitmap image displayed")
                        Log.d(TAG, "Size of quadbitmap image is ${selectPhotoViewModel.quadBitMapToDisplay.width} wide and ${selectPhotoViewModel.quadBitMapToDisplay.height} high")

                    }
                }


            }
        }
    }
}

