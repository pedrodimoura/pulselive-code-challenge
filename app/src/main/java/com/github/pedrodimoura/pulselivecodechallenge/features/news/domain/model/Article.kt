package com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model

data class Article(
    val id: Int,
    val title: String,
    val subtitle: String,
    val body: String,
    val date: String,
)
