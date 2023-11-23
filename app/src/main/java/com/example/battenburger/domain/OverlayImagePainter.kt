package com.example.battenburger.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Picture
import android.net.Uri
import android.os.Environment
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
import com.example.battenburger.R
import com.example.battenburger.saveIt
import kotlinx.coroutines.GlobalScope
import java.io.File

@Composable
fun UseCanvasToOverlay(bitmap1: Bitmap, context: Context) {
    val resources = context.resources
    val quadBitmap = provideQuadimage(bitmap1)

    val cakeImage = BitmapFactory.decodeResource(resources, R.drawable.battenburgslice2)
    val resizedCakeImage = Bitmap.createScaledBitmap(cakeImage, (quadBitmap.width*1.5).toInt(), (quadBitmap.height*1.25).toInt(),true)
    val aspectRatio = resizedCakeImage.width/resizedCakeImage.height

    val picture = remember { Picture() }


    Column(modifier = Modifier
        .fillMaxSize()
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
        }
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
        Button(onClick = {
            saveIt = createBitmapFromPicture(picture)

        })
        {
            Text(
                textAlign = TextAlign.Center,
                text = "Convert it!")
        }

        Button(onClick = {
            saveImageToMediaStore(context, "change this", saveIt )

        })
        {
            Text(
                textAlign = TextAlign.Center,
                text = "Save it!")
        }
    }

}

private fun createBitmapFromPicture(picture: Picture): Bitmap {
    val bitmap = Bitmap.createBitmap(
        picture.width,
        picture.height,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawColor(android.graphics.Color.WHITE)
    canvas.drawPicture(picture)
    return bitmap
}

//private suspend fun Bitmap.saveToDisk(context: Context): Uri {
//    val file = File(
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//        "screenshot-${System.currentTimeMillis()}.png"
//    )
//
//    file.writeBitmap(this, Bitmap.CompressFormat.PNG, 100)
//
//    //return scanFilePath(context, file.path) ?: throw Exception("File could not be saved")
//}

private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
    }
}
