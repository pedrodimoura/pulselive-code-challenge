package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.databinding.FragmentArticleDetailsBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.state.ArticleState
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleDetailsFragment : Fragment() {

    private lateinit var viewBinding: FragmentArticleDetailsBinding

    private val viewModel: NewsViewModel by activityViewModels()

    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_article_details, container, false)
        viewBinding = FragmentArticleDetailsBinding.bind(view)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarConfigs()
        setupObserver()
        fetchArticleDetails()
    }

    private fun setupToolbarConfigs() {
        viewBinding.mtArticleTitle.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setupObserver() {
        viewModel.articleUIStateFlow.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ArticleState.Loading -> showLoading()
                is ArticleState.Success -> showFullArticle(state.article)
                is ArticleState.Failure -> showErrorOnUI(state.message ?: getDefaultErrorMessage())
                is ArticleState.Done -> hideLoading()
            }
        }
    }

    private fun fetchArticleDetails() = viewModel.getArticleDetails(args.article)

    private fun showLoading() {
        viewBinding.pbArticleDetails.isVisible = true
        hideContent()
    }

    private fun hideLoading() {
        viewBinding.pbArticleDetails.isVisible = false
    }

    private fun hideContent() {
        viewBinding.tvBody.isVisible = false
    }

    private fun showContent() {
        viewBinding.tvBody.isVisible = true
    }

    private fun showFullArticle(article: Article) {
        viewBinding.apply {
            mtArticleTitle.title = article.title
            tvSubtitle.text = article.subtitle
            tvBody.text = article.body
        }
        showContent()
    }

    private fun showErrorOnUI(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    private fun getDefaultErrorMessage(): String = getString(R.string.default_error_message)
}
