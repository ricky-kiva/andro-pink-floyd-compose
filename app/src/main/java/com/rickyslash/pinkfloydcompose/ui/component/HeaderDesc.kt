package com.rickyslash.pinkfloydcompose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyslash.pinkfloydcompose.R

@Composable
fun HeaderDesc(title: String, desc: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.ext_add_dot, title),
            fontSize = 48.sp,
            modifier = modifier
                .padding(bottom = 8.dp)
        )
        Text(
            text = desc,
        )
    }
}