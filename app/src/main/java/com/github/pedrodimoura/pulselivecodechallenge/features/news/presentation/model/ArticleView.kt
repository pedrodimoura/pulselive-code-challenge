package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleView(
    val id: Int,
    val title: String,
    val subtitle: String,
    val body: String,
    val date: String,
) : Parcelable
