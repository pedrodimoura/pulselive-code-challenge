package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleDetailsResponse(
    @SerializedName("item")
    val item: ArticleResponse,
)
