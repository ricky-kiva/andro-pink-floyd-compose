package com.rickyslash.pinkfloydcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rickyslash.pinkfloydcompose.model.AlbumsDataSource
import com.rickyslash.pinkfloydcompose.ui.navigation.Screen
import com.rickyslash.pinkfloydcompose.ui.screen.about.AboutScreen
import com.rickyslash.pinkfloydcompose.ui.screen.albumdetail.AlbumDetailScreen
import com.rickyslash.pinkfloydcompose.ui.screen.fav.FavScreen
import com.rickyslash.pinkfloydcompose.ui.screen.home.HomeScreen
import com.rickyslash.pinkfloydcompose.ui.theme.PinkFloydComposeTheme

@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Box(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToFav = { navController.navigate(Screen.Favorite.route) },
                    navigateToAbout = { navController.navigate(Screen.About.route) },
                    navigateToDetail = { albumId ->
                        navController.navigate(Screen.DetailAlbum.createRoute(albumId))
                    },

                )
            }
            composable(Screen.Favorite.route) {
                FavScreen(
                    navigateBack = { navController.navigateUp() },
                    navigateToAbout = { navController.navigate(Screen.About.route) },
                    navigateToDetail = { albumId ->
                        navController.navigate(Screen.DetailAlbum.createRoute(albumId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen(
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable (
                route = Screen.DetailAlbum.route,
                arguments = listOf(navArgument("albumId") { type = NavType.LongType })
            ) {
                val albumId = it.arguments?.getLong("albumId") ?: -1L
                AlbumDetailScreen(
                    albumId = albumId,
                    navigateBack = { navController.navigateUp() },
                    prevCallback = {
                        if (albumId > 1) {
                            navController.popBackStack()
                            navController.navigate(Screen.DetailAlbum.createRoute(albumId - 1L)) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    nextCallback = {
                        if (albumId < (AlbumsDataSource.albumsData.size)) {
                            navController.popBackStack()
                            navController.navigate(Screen.DetailAlbum.createRoute(albumId + 1L)) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainComponentPreview() {
    PinkFloydComposeTheme(darkTheme = true) {
        Surface {
            MainComponent()
        }
    }
}