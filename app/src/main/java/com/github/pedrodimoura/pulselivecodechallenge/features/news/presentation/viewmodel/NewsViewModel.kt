package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
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
}
