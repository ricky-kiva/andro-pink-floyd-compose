package com.rickyslash.pinkfloydcompose.ui.screen.fav

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rickyslash.pinkfloydcompose.R
import com.rickyslash.pinkfloydcompose.helper.di.Injection
import com.rickyslash.pinkfloydcompose.helper.di.ViewModelFactory
import com.rickyslash.pinkfloydcompose.model.Album
import com.rickyslash.pinkfloydcompose.ui.common.UiState
import com.rickyslash.pinkfloydcompose.ui.component.AlbumList
import com.rickyslash.pinkfloydcompose.ui.component.HeaderDesc

@Composable
fun FavScreen(
    modifier: Modifier = Modifier,
    viewModel: FavViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateBack: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> { viewModel.getFavAlbums() }
            is UiState.Success -> {
                FavContent(
                    albums = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    navigateBack = navigateBack,
                    navigateToAbout = navigateToAbout
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavContent(
    modifier: Modifier = Modifier,
    albums: List<Album>,
    navigateToDetail: (Long) -> Unit,
    navigateBack: () -> Unit,
    navigateToAbout: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp, vertical = 32.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            FavTopBar(
                navigateBack = { navigateBack() },
                aboutCallback = { navigateToAbout() }
            )
        }
        Column(modifier = Modifier.padding(bottom = 36.dp)) {
            HeaderDesc(
                title = stringResource(R.string.label_fav),
                desc = stringResource(R.string.floyd_lyrics_breathe_reff)
            )
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
                    text = stringResource(R.string.label_empty_favorite),
                    fontSize = 11.sp,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun FavTopBar(
    navigateBack: () -> Unit,
    aboutCallback: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
                .clickable { navigateBack() }
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.desc_back),
                modifier = Modifier
                    .padding(12.dp)
                    .size(16.dp)
            )
        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
                .clickable { aboutCallback() }
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.about_page),
                modifier = Modifier
                    .padding(12.dp)
                    .size(16.dp)
            )
        }
    }
}