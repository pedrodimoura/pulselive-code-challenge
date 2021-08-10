package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.pedrodimoura.pulselivecodechallenge.common.data.model.DefaultNetworkError
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.News
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
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

    private val observer: Observer<NewsState> by lazy { mockk() }

    @Before
    fun setup() {
        every { observer.onChanged(any()) } just Runs
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

            newsViewModel.newsUIStateFlow.observeForever(observer)

            newsViewModel.getNews()

            coVerify(exactly = 1) { newsRepository.getNews() }

            verifySequence {
                observer.onChanged(NewsState.Loading)
                observer.onChanged(NewsState.Success(expectedResult.articles))
                observer.onChanged(NewsState.Done)
            }

        }
    }

    @Test
    fun shouldBeNotSuccessfulWhenGetNews() {
        runBlockingTest {
            val expectedException = DefaultNetworkError()
            coEvery { newsRepository.getNews() } throws expectedException

            newsViewModel.newsUIStateFlow.observeForever(observer)

            newsViewModel.getNews()

            coVerify(exactly = 1) { newsRepository.getNews() }

            verifySequence {
                observer.onChanged(NewsState.Loading)
                observer.onChanged(NewsState.Failure(expectedException.message))
                observer.onChanged(NewsState.Done)
            }

        }
    }

}