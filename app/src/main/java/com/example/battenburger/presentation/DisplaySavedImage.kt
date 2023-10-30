package com.example.battenburger.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.io.File

@Composable
fun DisplaySavedImage(){
    val context = LocalContext.current
    val image = File(context.filesDir, "image.jpg")
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = image,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Yellow, blendMode = BlendMode.Darken),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)))
            AsyncImage(
                model = image,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Darken),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)))
        }

        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = image,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Darken),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)))
            AsyncImage(
                model = image,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Yellow, blendMode = BlendMode.Darken),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp)))
        }

    }
}



