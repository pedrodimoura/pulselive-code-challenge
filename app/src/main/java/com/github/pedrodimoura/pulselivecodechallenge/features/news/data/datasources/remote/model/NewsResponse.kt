package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("items")
    val articles: List<ArticleResponse>,
)
