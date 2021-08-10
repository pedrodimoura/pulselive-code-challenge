package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.ArticleDetailsResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

const val NEWS_INFORMATION_ENDPOINT = "contentList.json"
const val ARTICLE_DETAILS_ENDPOINT = "content/{id}.json"

interface NewsService {

    @GET(NEWS_INFORMATION_ENDPOINT)
    suspend fun fetchNews(): NewsResponse

    @GET(ARTICLE_DETAILS_ENDPOINT)
    suspend fun fetchArticleDetails(@Path("id") articleId: Int): ArticleDetailsResponse
}
