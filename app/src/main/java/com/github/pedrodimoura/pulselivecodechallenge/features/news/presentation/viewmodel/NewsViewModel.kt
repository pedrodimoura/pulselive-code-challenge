package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.model.ArticleView
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.ArticleState
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _newsUIStateFlow = MutableLiveData<NewsState>()
    val newsUIStateFlow: LiveData<NewsState> = _newsUIStateFlow

    private val _articleUIStateFlow = MutableLiveData<ArticleState>()
    val articleUIStateFlow: LiveData<ArticleState> = _articleUIStateFlow

    fun getNews() {
        viewModelScope.launch {
            _newsUIStateFlow.value = NewsState.Loading
            runCatching {
                newsRepository.getNews()
            }.onSuccess {
                _newsUIStateFlow.value = NewsState.Success(it.articles)
            }.onFailure {
                _newsUIStateFlow.value = NewsState.Failure(it.message)
            }
            _newsUIStateFlow.value = NewsState.Done
        }
    }

    fun getArticleDetails(articleView: ArticleView) {
        viewModelScope.launch {
            _articleUIStateFlow.value = ArticleState.Loading
            runCatching {
                newsRepository.getArticleDetails(articleView.asDomain())
            }.onSuccess {
                _articleUIStateFlow.value = ArticleState.Success(it)
            }.onFailure {
                _articleUIStateFlow.value = ArticleState.Failure(it.message)
            }
            _articleUIStateFlow.value = ArticleState.Done
        }
    }

    private fun ArticleView.asDomain(): Article = Article(
        this.id,
        this.title,
        this.subtitle,
        this.body,
        this.date
    )
}
