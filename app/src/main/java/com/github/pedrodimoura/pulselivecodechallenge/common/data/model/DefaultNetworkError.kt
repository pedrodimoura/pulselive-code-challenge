package com.github.pedrodimoura.pulselivecodechallenge.common.data.model

class DefaultNetworkError(
    cause: Throwable? = null,
) : Throwable("Something went wrong! Please try again later.", cause)
