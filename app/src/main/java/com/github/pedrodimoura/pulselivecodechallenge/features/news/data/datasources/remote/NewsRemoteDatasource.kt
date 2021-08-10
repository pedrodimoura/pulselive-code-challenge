package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse

interface NewsRemoteDatasource {
    suspend fun getNews(): NewsResponse
}
