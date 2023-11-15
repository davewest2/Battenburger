package com.example.battenburger.presentation

import android.content.Context
import android.graphics.Bitmap
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
import com.example.battenburger.domain.DisplayBattenburgViewModel
import com.example.battenburger.domain.saveImageToMediaStore
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

// TODO: Optional permissions request code commented out.
//  API level < 29 (Android 10): WRITE_EXTERNAL_STORAGE required

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DisplayBattenburgScreen(navController: NavController){
    val viewModel = DisplayBattenburgViewModel()
    val context = LocalContext.current
//    val writeExeternalPermissionState = rememberPermissionState(
//        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

    DisplayBattenburg(context,"replace this string", viewModel.battenburg)

//    if (writeExeternalPermissionState.status.isGranted) {
//        DisplayBattenburg(context,"replace this string", viewModel.battenburg)
//    } else {
//        Column {
//            val textToShow = if (writeExeternalPermissionState.status.shouldShowRationale) {
//                "Access is required in order to save your Battenburg! Please grant the permission."
//            } else {
//                // If it's the first time the user lands on this feature, or the user
//                // doesn't want to be asked again for this permission, explain that the
//                // permission is required
//                "Access permission required for this feature to be available. " +
//                        "Please grant the permission"
//            }
//            Text(textToShow)
//            Button(onClick = { writeExeternalPermissionState.launchPermissionRequest() }) {
//                Text("Request permission")
//            }
//        }
//    }
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



