package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.databinding.ActivityMainBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter.ArticlesAdapter
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.NewsState
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: NewsViewModel by viewModels()

    private val articlesAdapter: ArticlesAdapter by lazy { ArticlesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupObservers()
        setupListeners()
        setupView()
        fetchNews()
    }

    private fun setupObservers() {
        viewModel.newsUIStateFlow.observe(this) { state ->
            when (state) {
                is NewsState.Loading -> showLoading()
                is NewsState.Success -> showDataOnUI(state.articles)
                is NewsState.Failure -> showErrorOnUI(state.message ?: getDefaultErrorMessage())
                is NewsState.Done -> hideLoading()
            }
        }
    }

    private fun setupListeners() {
        articlesAdapter.onArticleClick = {
            showErrorOnUI("Open Details Activity")
        }
    }

    private fun setupView() {
        viewBinding.rvArticles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
        }
    }

    private fun fetchNews() {
        viewModel.getNews()
    }

    private fun showLoading() {
        viewBinding.pbArticles.isVisible = true
        hideContent()
    }

    private fun showDataOnUI(articles: List<Article>) {
        articlesAdapter.submitList(articles)
        showContent()
    }

    private fun showErrorOnUI(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    private fun showContent() {
        viewBinding.rvArticles.isVisible = true
    }

    private fun hideContent() {
        viewBinding.rvArticles.isVisible = false
    }

    private fun hideLoading() {
        viewBinding.pbArticles.isVisible = false
    }

    private fun getDefaultErrorMessage() = getString(R.string.default_error_message)
}