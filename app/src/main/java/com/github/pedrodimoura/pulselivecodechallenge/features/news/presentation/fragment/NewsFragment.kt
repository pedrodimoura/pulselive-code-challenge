package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.databinding.FragmentNewsBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter.ArticlesAdapter
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.NewsState
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var viewBinding: FragmentNewsBinding

    private val viewModel: NewsViewModel by viewModels()

    private val articlesAdapter: ArticlesAdapter by lazy { ArticlesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.fragment_news, container, false)
        viewBinding = FragmentNewsBinding.bind(view)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        setupView()
        fetchNews()
    }

    private fun setupObservers() {
        viewModel.newsUIStateFlow.observe(viewLifecycleOwner) { state ->
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
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()

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