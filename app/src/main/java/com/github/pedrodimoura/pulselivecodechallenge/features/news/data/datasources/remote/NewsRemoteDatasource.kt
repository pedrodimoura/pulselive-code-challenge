package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.ArticleResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse

interface NewsRemoteDatasource {
    suspend fun getNews(): NewsResponse
    suspend fun getArticleDetails(id: Int): ArticleResponse
}
