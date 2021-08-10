package com.github.pedrodimoura.pulselivecodechallenge.common.idling

import androidx.test.espresso.IdlingResource

class SupportResourceCallback(
    private val resourceCallback: IdlingResource.ResourceCallback
) : android.support.test.espresso.IdlingResource.ResourceCallback {
    override fun onTransitionToIdle() = resourceCallback.onTransitionToIdle()
}
