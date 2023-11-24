package com.example.battenburger

sealed class Screen(val route: String) {

    object SelectPhotoScreen: Screen(route = "selectphoto_screen")
    object BattenburgProcessingScreen: Screen(route = "cropimage_screen")
    object DisplayBattenburgScreen: Screen(route = "displaybattenburg_screen")

}
