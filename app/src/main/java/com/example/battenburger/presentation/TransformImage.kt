package com.example.battenburger.presentation

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.imageBitMap


@Composable
fun TransformImage() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
      Row(
          modifier = Modifier.fillMaxWidth()
      ) {
         AsyncImage(
             model = imageBitMap,
             contentDescription = null,
             colorFilter = ColorFilter.tint(Color.Yellow, blendMode = BlendMode.Darken),
             modifier = Modifier
                 .size(200.dp)
                 .clip(RoundedCornerShape(16.dp)),
             contentScale = ContentScale.Crop
         )
      }
    }
}