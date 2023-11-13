package com.example.battenburger.presentation

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.battenburger.quadImageBitMap
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File

@Composable
fun DisplayBattenburgScreen(navController: NavController){
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


        }
    }

// Below from https://gist.github.com/chiragthummar/35aa1d15882c4d5a105a3f89be821214 additional functions to save images to disk
//private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
//    outputStream().use { out ->
//        bitmap.compress(format, quality, out)
//        out.flush()
//    }
//}


//private suspend fun Bitmap.saveToDisk(context: Context): Uri {
//    val file = File(
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//        "screenshot-${System.currentTimeMillis()}.png"
//    )
//
//    file.writeBitmap(this, Bitmap.CompressFormat.PNG, 100)
//
//    return scanFilePath(context, file.path) ?: throw Exception("File could not be saved")
//}
//
//private suspend fun scanFilePath(context: Context, filePath: String): Uri? {
//    return suspendCancellableCoroutine { continuation ->
//        MediaScannerConnection.scanFile(
//            context,
//            arrayOf(filePath),
//            arrayOf("image/png")
//        ) { _, scannedUri ->
//            if (scannedUri == null) {
//                continuation.cancel(Exception("File $filePath could not be scanned"))
//            } else {
//                continuation.resume(scannedUri)
//            }
//        }
//    }
//}



