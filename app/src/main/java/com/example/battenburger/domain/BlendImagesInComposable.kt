package com.example.battenburger.domain

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.battenburger.R

//@Composable
//fun BlendImagesInComposable(bitmap1: Bitmap, bitmap2: Bitmap){
//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        val rainbowImage = ImageBitmap.imageResource(id = R.drawable.katie_summer_cross_r2_14_june_2023)
//        val dogImage = ImageBitmap.imageResource(id = R.drawable.battenburgslice2)
//        val customPainter = remember {
//            OverlayImagePainter(dogImage, rainbowImage)
//        }
//        Image(
//            painter = customPainter,
//            contentDescription = "hello",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.wrapContentSize()
//        )
//        Box(
//            modifier = Modifier
//                .align(CenterHorizontally)
//                .fillMaxWidth()
//                .size(400.dp)
//        )  {
//            Image(
//                bitmap = bitmap1.asImageBitmap(),
//                contentDescription = "base bitmap",
//                modifier = Modifier
//                    .align(Center)
//                    .fillMaxWidth()
//            )
//            Image(
//                bitmap = bitmap2.asImageBitmap(),
//                contentDescription = "overlay bitmap",
//                modifier = Modifier
//                    .align(Center)
//                    .fillMaxWidth()
//            )
//        }
//    }
//}