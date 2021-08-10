package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.impl

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.NewsRemoteDatasource
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service.NewsService

internal class NewsRemoteDatasourceImpl(
    private val newsService: NewsService,
) : NewsRemoteDatasource {

    override suspend fun getNews(): NewsResponse = newsService.fetchNews()
}
