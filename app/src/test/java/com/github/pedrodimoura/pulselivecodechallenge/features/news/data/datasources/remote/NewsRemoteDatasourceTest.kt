package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.impl.NewsRemoteDatasourceImpl
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service.NewsService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class NewsRemoteDatasourceTest {

    private val newsService: NewsService by lazy { mockk() }
    private val newsRemoteDatasource: NewsRemoteDatasource by lazy {
        NewsRemoteDatasourceImpl(newsService)
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun shouldBeSuccessfulWhenFetchNews() {
        runBlockingTest {
            coEvery { newsService.fetchNews() } returns mockk()

            newsRemoteDatasource.getNews()

            coVerify(exactly = 1) { newsService.fetchNews() }
        }
    }

    @Test(expected = HttpException::class)
    fun shouldBeNotSuccessfulWhenFetchNews() {
        runBlockingTest {
            coEvery { newsService.fetchNews() } throws mockk<HttpException>()

            newsRemoteDatasource.getNews()

            coVerify(exactly = 1) { newsService.fetchNews() }
        }
    }
}
