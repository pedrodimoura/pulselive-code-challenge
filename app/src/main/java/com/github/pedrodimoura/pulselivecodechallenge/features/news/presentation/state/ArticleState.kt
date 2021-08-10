package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state

import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article

sealed class ArticleState {
    object Loading : ArticleState()
    data class Success(val article: Article) : ArticleState()
    data class Failure(val message: String? = null) : ArticleState()
    object Done : ArticleState()
}
