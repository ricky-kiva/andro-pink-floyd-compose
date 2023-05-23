package com.rickyslash.pinkfloydcompose.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.rickyslash.pinkfloydcompose.ui.component.HeaderDesc
import com.rickyslash.pinkfloydcompose.ui.component.SearchBar
import com.rickyslash.pinkfloydcompose.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToFav: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> { viewModel.getAllAlbums() }
            is UiState.Success -> {
                    HomeContent(
                        modifier = modifier,
                        albums = uiState.data,
                        query = query,
                        navigateToDetail = navigateToDetail,
                        navigateToFav = navigateToFav,
                        navigateToAbout = navigateToAbout,
                        viewModel = viewModel
                    )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    albums: List<Album>,
    query: String,
    navigateToDetail: (Long) -> Unit,
    navigateToFav: () -> Unit,
    navigateToAbout: () -> Unit,
    viewModel: HomeViewModel
) {
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp, vertical = 32.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            MainTopBar(
                aboutCallback = { navigateToAbout() },
                favCallback = { navigateToFav() }
            )
        }
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            HeaderDesc(
                title = stringResource(R.string.band_floyd_name),
                desc = stringResource(R.string.band_floyd_desc)
            )
        }
        Column(modifier = Modifier.padding(bottom = 36.dp)) {
            SearchBar(query = query, onQueryChange = viewModel::search)
        }
        Divider(color = MaterialTheme.colors.onSecondary.copy(alpha = 0.25f))
        if (albums.isNotEmpty()) {
            AlbumList(albumList = albums, navigateToDetail = navigateToDetail)
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.label_empty_albums),
                    fontSize = 11.sp,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
            }
        }
    }
}

