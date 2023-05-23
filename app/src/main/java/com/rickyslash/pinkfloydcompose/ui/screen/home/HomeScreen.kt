package com.rickyslash.pinkfloydcompose.ui.screen.home

import androidx.compose.foundation.layout.*
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
import com.rickyslash.pinkfloydcompose.ui.component.MainTopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit,
    navigateToFav: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> { viewModel.getAllAlbums() }
            is UiState.Success -> {
                Column(
                    modifier = modifier
                        .padding(horizontal = 32.dp, vertical = 32.dp)
                ) {
                    Column(modifier = Modifier.padding(bottom = 32.dp)) {
                        MainTopBar(
                            aboutCallback = {},
                            favCallback = navigateToFav
                        )
                    }
                    HomeContent(
                        albums = uiState.data,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    albums: List<Album>,
    navigateToDetail: (Long) -> Unit
) {
    AlbumList(albumList = albums, navigateToDetail = navigateToDetail)
}

