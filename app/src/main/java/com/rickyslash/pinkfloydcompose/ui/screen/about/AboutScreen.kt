package com.rickyslash.pinkfloydcompose.ui.screen.about

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rickyslash.pinkfloydcompose.R

@Composable
fun AboutScreen(
    navigateBack: () -> Unit
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 32.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.padding(bottom = 32.dp)) {
                AboutTopBar(
                    navigateBack = navigateBack,
                    uriHandler = uriHandler
                )
            }
            AboutContent(
                id = "0",
                name = stringResource(R.string.dev_name),
                title = stringResource(R.string.label_about),
                date = stringResource(R.string.dev_release_date),
                email =  stringResource(R.string.dev_email),
                imageUrl = stringResource(R.string.dev_photo_link),
                motto = stringResource(R.string.dev_motto)
            )
        }
    }
}

@Composable
fun AboutContent(
    id: String,
    name: String,
    title: String,
    date: String,
    email: String,
    imageUrl: String,
    motto: String
) {
    LazyColumn {
        item {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 9.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onSecondary)
                    ) {
                        Text(
                            text = id,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.background,
                        )
                    }
                    Text(
                        text = date.uppercase(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (0).sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.ext_add_dot, title),
                    fontSize = 36.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = 12.dp, bottom = 18.dp)
                )
                Box(
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = stringResource(R.string.desc_album_name, title),
                        placeholder = painterResource(R.drawable.img_placeholder_dsod_wembley),
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = motto,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Row {
                    Text(
                        text = name,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "―",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    )
                    Text(
                        text = email,
                        fontSize = 11.sp,
                        color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
fun AboutTopBar(
    navigateBack: () -> Unit,
    uriHandler: UriHandler
) {
    val webUri = stringResource(R.string.dev_web_url)
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
                .clickable { uriHandler.openUri(webUri) }
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.desc_dev_website),
                modifier = Modifier
                    .padding(12.dp)
                    .size(16.dp)
            )
        }
    }
}