package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.pedrodimoura.pulselivecodechallenge.common.data.model.DefaultNetworkError
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.News
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.ArticleState
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.NewsState
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule by lazy { InstantTaskExecutorRule() }

    private val testCoroutineDispatcher: TestCoroutineDispatcher by lazy {
        TestCoroutineDispatcher()
    }

    private val newsRepository: NewsRepository by lazy { mockk() }

    private val newsViewModel: NewsViewModel by lazy { NewsViewModel(newsRepository) }

    private val observerNews: Observer<NewsState> by lazy { mockk() }

    private val observerArticle: Observer<ArticleState> by lazy { mockk() }

    @Before
    fun setup() {
        every { observerNews.onChanged(any()) } just Runs
        every { observerArticle.onChanged(any()) } just Runs
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun shouldBeSuccessfulWhenGetNews() {
        runBlockingTest {
            val expectedResult = News(emptyList())
            coEvery { newsRepository.getNews() } returns expectedResult

            newsViewModel.newsUIStateFlow.observeForever(observerNews)

            newsViewModel.getNews()

            coVerify(exactly = 1) { newsRepository.getNews() }

            verifySequence {
                observerNews.onChanged(NewsState.Loading)
                observerNews.onChanged(NewsState.Success(expectedResult.articles))
                observerNews.onChanged(NewsState.Done)
            }

        }
    }

    @Test
    fun shouldBeNotSuccessfulWhenGetNews() {
        runBlockingTest {
            val expectedException = DefaultNetworkError()
            coEvery { newsRepository.getNews() } throws expectedException

            newsViewModel.newsUIStateFlow.observeForever(observerNews)

            newsViewModel.getNews()

            coVerify(exactly = 1) { newsRepository.getNews() }

            verifySequence {
                observerNews.onChanged(NewsState.Loading)
                observerNews.onChanged(NewsState.Failure(expectedException.message))
                observerNews.onChanged(NewsState.Done)
            }

        }
    }

    @Test
    fun shouldBeSuccessfulWhenGetArticleDetails() {
        runBlockingTest {
            val expectedResult = mockk<Article>(relaxed = true)
            coEvery { newsRepository.getArticleDetails(any()) } returns expectedResult

            newsViewModel.articleUIStateFlow.observeForever(observerArticle)

            newsViewModel.getArticleDetails(mockk(relaxed = true))

            coVerify(exactly = 1) { newsRepository.getArticleDetails(any()) }

            verifySequence {
                observerArticle.onChanged(ArticleState.Loading)
                observerArticle.onChanged(ArticleState.Success(expectedResult))
                observerArticle.onChanged(ArticleState.Done)
            }

        }
    }

    @Test
    fun shouldBeNotSuccessfulWhenGetArticleDetails() {
        runBlockingTest {
            val expectedException = DefaultNetworkError()
            coEvery { newsRepository.getArticleDetails(any()) } throws expectedException

            newsViewModel.articleUIStateFlow.observeForever(observerArticle)

            newsViewModel.getArticleDetails(mockk(relaxed = true))

            coVerify(exactly = 1) { newsRepository.getArticleDetails(any()) }

            verifySequence {
                observerArticle.onChanged(ArticleState.Loading)
                observerArticle.onChanged(ArticleState.Failure(expectedException.message))
                observerArticle.onChanged(ArticleState.Done)
            }

        }
    }
}