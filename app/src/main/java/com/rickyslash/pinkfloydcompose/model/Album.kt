package com.rickyslash.pinkfloydcompose.model

data class Album(
    val id: Long,
    val title: String,
    val release: String,
    val song: String,
    val imageUrl: String,
    val desc: String
)