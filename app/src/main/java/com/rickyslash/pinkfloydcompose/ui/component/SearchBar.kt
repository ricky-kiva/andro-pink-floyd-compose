package com.rickyslash.pinkfloydcompose.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rickyslash.pinkfloydcompose.R

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(stringResource(id = R.string.desc_search)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(64.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(64.dp),
                color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
            )
    )
}