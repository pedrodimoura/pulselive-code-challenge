package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("body")
    val body: String?,
)
