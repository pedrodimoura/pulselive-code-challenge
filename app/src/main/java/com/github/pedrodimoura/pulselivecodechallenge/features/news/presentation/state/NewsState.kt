package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state

import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article

sealed class NewsState {
    object Loading : NewsState()
    data class Success(val articles: List<Article>) : NewsState()
    data class Failure(val message: String? = null) : NewsState()
    object Done : NewsState()
}
