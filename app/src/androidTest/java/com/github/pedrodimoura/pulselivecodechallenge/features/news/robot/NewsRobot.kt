package com.github.pedrodimoura.pulselivecodechallenge.features.news.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.pedrodimoura.pulselivecodechallenge.R
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.adapter.ArticlesAdapter

class NewsRobot {
    fun clickArticle(): NewsResult {
        onView(withId(R.id.rvArticles)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ArticlesAdapter.ArticleViewHolder>(
                1,
                click()
            )
        )
        return NewsResult()
    }
}