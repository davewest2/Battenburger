package com.example.battenburger

sealed class Screen(val route: String) {
    object BattenburgerThemeScreen: Screen(route = "battenburgtheme_screen")
    object TransformImageScreen: Screen(route = "transformimage_screen")
    //object AttachCameraScreen: Screen(route = "attachcamera_screen")

}
