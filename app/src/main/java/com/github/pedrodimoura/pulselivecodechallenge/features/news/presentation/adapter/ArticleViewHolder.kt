package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.pedrodimoura.pulselivecodechallenge.databinding.ArticleItemBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article

class ArticleViewHolder(
    private val articleItemBinding: ArticleItemBinding,
    private val onArticleClick: OnArticleClick?,
) :
    RecyclerView.ViewHolder(articleItemBinding.root) {

    fun bind(article: Article) {
        articleItemBinding.tvTitle.text = article.title
        articleItemBinding.tvSubtitle.text = article.subtitle
        articleItemBinding.tvDate.text = article.date
        articleItemBinding.articleCard.setOnClickListener { onArticleClick?.invoke(article) }
    }
}
