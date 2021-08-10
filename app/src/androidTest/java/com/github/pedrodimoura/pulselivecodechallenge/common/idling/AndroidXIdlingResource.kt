package com.github.pedrodimoura.pulselivecodechallenge.common.idling

import androidx.test.espresso.IdlingResource
import com.github.pedrodimoura.pulselivecodechallenge.common.idling.asSupport

class AndroidXIdlingResource(
    private val supportIdlingResource: android.support.test.espresso.IdlingResource,
) : IdlingResource {

    override fun getName(): String = supportIdlingResource.name

    override fun isIdleNow(): Boolean = supportIdlingResource.isIdleNow

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) =
        supportIdlingResource.registerIdleTransitionCallback(callback.asSupport())
}
