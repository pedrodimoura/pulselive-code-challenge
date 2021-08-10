package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.ArticleResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("contentList.json")
    suspend fun fetchNews(): NewsResponse

    @GET("{id}.json")
    suspend fun fetchArticleDetails(@Path("id") articleId: Int): ArticleResponse
}
