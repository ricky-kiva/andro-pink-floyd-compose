package com.rickyslash.pinkfloydcompose.model

data class Album(
    val id: Long,
    val title: String,
    val release: String,
    val song: String,
    val photoUrl: String,
    val desc: String
)