package com.rickyslash.pinkfloydcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Favorite: Screen("fav")
    object About: Screen("about")
    object DetailAlbum: Screen("home/{albumId}") {
        fun createRoute(albumId: Long) = "home/$albumId"
    }
}