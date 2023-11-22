package com.example.battenburger.domain

// TODO: If needed for SDK < 29

//    val writeExeternalPermissionState = rememberPermissionState(
//        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

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