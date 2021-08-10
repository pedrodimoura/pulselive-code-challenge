package com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository

import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.News

interface NewsRepository {
    suspend fun getNews(): News
    suspend fun getArticleDetails(article: Article): Article
}
