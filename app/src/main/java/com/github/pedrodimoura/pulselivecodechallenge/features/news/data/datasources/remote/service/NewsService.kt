package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse
import retrofit2.http.GET

interface NewsService {

    @GET("contentList.json")
    suspend fun fetchNews(): NewsResponse
}
