package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.repository

import com.github.pedrodimoura.pulselivecodechallenge.common.data.ifThrowParseError
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.NewsRemoteDatasource
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.ArticleResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.model.NewsResponse
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.News
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDatasource: NewsRemoteDatasource,
) : NewsRepository {
    override suspend fun getNews(): News {
        return runCatching { newsRemoteDatasource.getNews().asDomain() }.ifThrowParseError()
    }

    override suspend fun getArticleDetails(article: Article): Article {
        return runCatching {
            newsRemoteDatasource.getArticleDetails(article.id).asDomain()
        }.ifThrowParseError()
    }

    private fun NewsResponse.asDomain(): News = News(
        this.articles.map { articleResponse ->
            Article(
                articleResponse.id,
                articleResponse.title,
                articleResponse.subtitle,
                articleResponse.body.orEmpty(),
                articleResponse.date
            )
        }
    )

    private fun ArticleResponse.asDomain(): Article = Article(
        this.id,
        this.title,
        this.subtitle,
        this.body.orEmpty(),
        this.date
    )
}
