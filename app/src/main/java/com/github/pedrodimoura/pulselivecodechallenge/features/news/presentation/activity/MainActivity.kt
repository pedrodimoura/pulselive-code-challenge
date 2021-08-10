package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.databinding.ActivityMainBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.NewsState
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.newsUIStateFlow.observe(this) { state ->
            when (state) {
                is NewsState.Loading -> showLoading()
                is NewsState.Success -> showDataOnUI(state.articles)
                is NewsState.Failure -> showErrorOnUI(state.message ?: getDefaultErrorMessage())
                is NewsState.Done -> hideLoading()
            }
        }
    }

    private fun showLoading() {

    }

    private fun showDataOnUI(articles: List<Article>) {

    }

    private fun showErrorOnUI(message: String) {

    }

    private fun hideLoading() {

    }

    private fun getDefaultErrorMessage() = getString(R.string.default_error_message)
}