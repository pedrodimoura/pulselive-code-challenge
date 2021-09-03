package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article

class ArticlesDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        (oldItem == newItem)

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        ((oldItem.id == newItem.id) and (oldItem.body == newItem.body))
}
