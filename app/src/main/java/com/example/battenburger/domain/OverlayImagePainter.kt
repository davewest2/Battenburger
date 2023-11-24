package com.example.battenburger.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Picture
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.battenburger.R
import com.example.battenburger.quadImageBitMap
import com.example.battenburger.saveIt

@Composable
fun UseCanvasToOverlay(bitmap1: Bitmap, context: Context) {
    val resources = context.resources
    val cakeImage = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice2)
    val resizedCakeImage = Bitmap.createScaledBitmap(cakeImage, (quadImageBitMap.width*1.5).toInt(), (quadImageBitMap.height*1.25).toInt(),true)
    val aspectRatio = resizedCakeImage.width/resizedCakeImage.height
    val picture = remember { Picture() }

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .size(75.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .drawWithCache {
                    val width = this.size.width.toInt()
                    val height = this.size.height.toInt()
                    onDrawWithContent {
                        val pictureCanvas =
                            androidx.compose.ui.graphics.Canvas(
                                picture.beginRecording(
                                    width,
                                    height
                                )
                            )
                        draw(this, this.layoutDirection, pictureCanvas, this.size) {
                            this@onDrawWithContent.drawContent()
                        }
                        picture.endRecording()

                        drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }
                    }
                },
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
                drawImage(
                    quadImageBitMap
                    .asImageBitmap(),
                    topLeft = Offset(180f, 150f),
                    blendMode = BlendMode.Hardlight
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .size(75.dp)
        )
        Button(
            onClick = {
            saveIt = createBitmapFromPicture(picture, context)
        },
            elevation = ButtonDefaults.buttonElevation(20.dp,0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta, contentColor = Color.Yellow)
        )
        {
            Text(
                textAlign = TextAlign.Center,
                text = "Save it!")
        }
    }
}

private fun createBitmapFromPicture(picture: Picture, context: Context): Bitmap {
    val bitmap = Bitmap.createBitmap(
        picture.width,
        picture.height,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawColor(android.graphics.Color.WHITE)
    canvas.drawPicture(picture)
    saveIt = bitmap
    saveImageToMediaStore(context, "change this", saveIt )
    return bitmap
}

