package com.rickyslash.pinkfloydcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rickyslash.pinkfloydcompose.model.AlbumsDataSource
import com.rickyslash.pinkfloydcompose.ui.navigation.Screen
import com.rickyslash.pinkfloydcompose.ui.theme.PinkFloydComposeTheme
import com.rickyslash.pinkfloydcompose.utils.assertCurrentRouteName
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainComponentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PinkFloydComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MainComponent(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailAlbum.route)
        composeTestRule.onNodeWithText(("${AlbumsDataSource.albumsData[10].title}.")).assertIsDisplayed()
    }

    @Test
    fun navHost_clickItem_navigatesToAbout() {
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.about_page)).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.dev_name)).assertIsDisplayed()
    }

    @Test
    fun navHost_addItem_navigatesToFav() {
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailAlbum.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_add_fav)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.fav_page)).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailAlbum.route)
        composeTestRule.onNodeWithText(("${AlbumsDataSource.albumsData[10].title}.")).assertIsDisplayed()
    }

    @Test
    fun navHost_negativeCase_deletedFavItem() {
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(5)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[5].title).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_add_fav)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.fav_page)).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[5].title).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_add_fav)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_back)).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[5].title).assertDoesNotExist()
    }

    @Test
    fun navHost_endToEnd_rightBackStack() {
        composeTestRule.onNodeWithTag("AlbumList").performScrollToIndex(AlbumsDataSource.albumsData.size-1)
        composeTestRule.onNodeWithText(AlbumsDataSource.albumsData[AlbumsDataSource.albumsData.size-1].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailAlbum.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_next)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_next)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_prev)).performClick()
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.fav_page)).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.desc_back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}