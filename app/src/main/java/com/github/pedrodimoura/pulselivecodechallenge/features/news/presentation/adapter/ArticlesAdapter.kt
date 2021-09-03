package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.databinding.ArticleItemBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article

typealias OnArticleClick = (Article) -> Unit

class ArticlesAdapter :
    ListAdapter<Article, ArticleViewHolder>(ArticlesDiffUtil()) {

    var onArticleClick: OnArticleClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(ArticleItemBinding.bind(view), onArticleClick)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bind(getItem(position))
}
