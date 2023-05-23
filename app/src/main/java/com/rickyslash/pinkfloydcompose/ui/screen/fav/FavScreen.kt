package com.rickyslash.pinkfloydcompose.ui.screen.fav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rickyslash.pinkfloydcompose.helper.di.Injection
import com.rickyslash.pinkfloydcompose.helper.di.ViewModelFactory
import com.rickyslash.pinkfloydcompose.model.Album
import com.rickyslash.pinkfloydcompose.ui.common.UiState
import com.rickyslash.pinkfloydcompose.ui.component.AlbumList
import com.rickyslash.pinkfloydcompose.ui.screen.home.HomeViewModel

@Composable
fun FavScreen(
    modifier: Modifier = Modifier,
    viewModel: FavViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> { viewModel.getFavAlbums() }
            is UiState.Success -> {
                FavContent(
                    albums = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavContent(
    albums: List<Album>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp)
    ) {
        AlbumList(albumList = albums, navigateToDetail = navigateToDetail)
    }
}