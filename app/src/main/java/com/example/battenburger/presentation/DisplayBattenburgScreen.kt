package com.example.battenburger.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.R
import com.example.battenburger.domain.DisplayBattenburgViewModel
import com.example.battenburger.domain.UseCanvasToOverlay
import com.example.battenburger.domain.saveImageToMediaStore
import com.example.battenburger.quadImageBitMap
import com.example.battenburger.selectedImageBitMap
import com.google.accompanist.permissions.ExperimentalPermissionsApi

// TODO: Optional permissions request code commented out.
//  API level < 29 (Android 10): WRITE_EXTERNAL_STORAGE required

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DisplayBattenburgScreen(navController: NavController){
    val viewModel = DisplayBattenburgViewModel()
    val context = LocalContext.current
    val resources = context.resources
    val cakeImage = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice2)

UseCanvasToOverlay(bitmap1 = selectedImageBitMap, context = context)
   // DisplayBattenburg(context,"replace this string", viewModel.battenburg)
    //BlendImagesInComposable(bitmap1 = quadImageBitMap, bitmap2 = cakeImage)
    
}

@Composable
fun DisplayBattenburg(context: Context, displayName: String, bitmap: Bitmap){
    val viewModel = DisplayBattenburgViewModel()
    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(100.dp))

        AsyncImage(
            model = viewModel.battenburg,
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(100.dp))

        Button(onClick = {
            saveImageToMediaStore(context, displayName, bitmap)
        })
        {
            Text(
                textAlign = TextAlign.Center,
                text = "Save it!")
        }
    }
}



