package com.github.pedrodimoura.pulselivecodechallenge.features.news.data.repository

import com.github.pedrodimoura.pulselivecodechallenge.common.data.model.DefaultNetworkError
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.NewsRemoteDatasource
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
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
class NewsRepositoryTest {

    private val newsRemoteDatasource: NewsRemoteDatasource by lazy { mockk() }
    private val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(newsRemoteDatasource)
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun shouldBeSuccessfulWhenGetNews() {
        runBlockingTest {
            coEvery { newsRemoteDatasource.getNews() } returns mockk(relaxed = true)

            newsRepository.getNews()

            coVerify(exactly = 1) { newsRemoteDatasource.getNews() }
        }
    }

    @Test(expected = DefaultNetworkError::class)
    fun shouldBeNotSuccessfulWhenGetNews() {
        runBlockingTest {
            coEvery { newsRemoteDatasource.getNews() } throws mockk<HttpException>()

            newsRepository.getNews()

            coVerify(exactly = 1) { newsRemoteDatasource.getNews() }
        }
    }

    @Test
    fun shouldBeSuccessfulWhenGetArticleDetails() {
        runBlockingTest {
            coEvery { newsRemoteDatasource.getArticleDetails(any()) } returns mockk(relaxed = true)

            newsRepository.getArticleDetails(mockk(relaxed = true))

            coVerify(exactly = 1) { newsRemoteDatasource.getArticleDetails(any()) }
        }
    }

    @Test(expected = DefaultNetworkError::class)
    fun shouldBeNotSuccessfulWhenGetArticleDetails() {
        runBlockingTest {
            coEvery { newsRemoteDatasource.getArticleDetails(any()) } throws mockk<HttpException>()

            newsRepository.getArticleDetails(mockk(relaxed = true))

            coVerify(exactly = 1) { newsRemoteDatasource.getArticleDetails(any()) }
        }
    }

}
