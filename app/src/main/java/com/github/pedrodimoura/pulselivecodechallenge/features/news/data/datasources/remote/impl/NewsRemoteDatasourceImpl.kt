package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.impl

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.NewsRemoteDatasource
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.ArticleResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service.NewsService
import javax.inject.Inject

class NewsRemoteDatasourceImpl @Inject constructor(
    private val newsService: NewsService,
) : NewsRemoteDatasource {

    override suspend fun getNews(): NewsResponse = newsService.fetchNews()

    override suspend fun getArticleDetails(id: Int): ArticleResponse =
        newsService.fetchArticleDetails(id).item
}
