package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.databinding.ArticleItemBinding
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.model.Article

typealias OnArticleClick = (Article) -> Unit

class ArticlesAdapter : ListAdapter<Article, ArticlesAdapter.ArticleViewHolder>(object :
    DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        (oldItem == newItem)

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        (oldItem.id == newItem.id)
}) {

    var onArticleClick: OnArticleClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(ArticleItemBinding.bind(view), onArticleClick)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bind(getItem(position))

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

}