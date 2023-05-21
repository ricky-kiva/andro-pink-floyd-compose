package com.rickyslash.pinkfloydcompose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rickyslash.pinkfloydcompose.ui.navigation.Screen
import com.rickyslash.pinkfloydcompose.ui.screen.home.HomeScreen
import com.rickyslash.pinkfloydcompose.ui.theme.PinkFloydComposeTheme

@Composable
fun MainComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = {
                    navController.navigate(Screen.DetailAlbum.createRoute(it))
                }
            )
        }
        composable(
            route = Screen.DetailAlbum.route,
            arguments = listOf(navArgument("albumId") { type = NavType.LongType})
        ) {
            val id = it.arguments?.getLong("albumId") ?: -1L
        }
    }
}

@Composable
fun MainTopBar(onMenuClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.about_page),
                modifier = Modifier
                    .padding(8.dp)
                    .size(11.dp)
            )
        }
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(R.string.fav_page),
                modifier = Modifier
                    .padding(8.dp)
                    .size(11.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainComponentPreview() {
    PinkFloydComposeTheme(darkTheme = true) {
        Surface {
            MainTopBar {}
            MainComponent()
        }
    }
}