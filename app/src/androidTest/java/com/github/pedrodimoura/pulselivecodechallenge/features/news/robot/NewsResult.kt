package com.github.pedrodimoura.pulselivecodechallenge.features.news.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.github.pedrodimoura.pulselivecodechallenge.R

class NewsResult {
    fun checkIfBodyIsDisplayed(): NewsResult {
        onView(withId(R.id.tvBody)).check(matches(isDisplayed()))
        return this
    }
}