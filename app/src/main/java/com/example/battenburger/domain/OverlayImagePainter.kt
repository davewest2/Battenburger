package com.example.battenburger.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import com.example.battenburger.R

@Composable
fun UseCanvasToOverlay(bitmap1: Bitmap, context: Context) {
    val resources = context.resources
    val quadBitmap = provideQuadimage(bitmap1)

    val cakeImage = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice2)
    val resizedCakeImage = Bitmap.createScaledBitmap(cakeImage, (quadBitmap.width*1.5).toInt(), (quadBitmap.height*1.25).toInt(),true)
    val aspectRatio = resizedCakeImage.width/resizedCakeImage.height

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            Alignment.Center,
        )
        {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio.toFloat())
                    .background(Color.Magenta)

            ) {
                val canvasWidth = size.width.toInt()
                val canvasHeight = size.height.toInt()
                drawImage(resizedCakeImage
                    .asImageBitmap(),
                    dstSize = IntSize(canvasWidth, canvasHeight)
                )
                drawImage(quadBitmap
                    .asImageBitmap(),
                    topLeft = Offset(180f, 150f),
                    blendMode = BlendMode.Hardlight
                )
            }
        }
        // TODO: Implement the button to save the composable as an image file
//        Button(onClick = {
//            saveImageToMediaStore(context, displayName, bitmap)
//        })
//        {
//            Text(
//                textAlign = TextAlign.Center,
//                text = "Save it!")
//        }
    }

}
