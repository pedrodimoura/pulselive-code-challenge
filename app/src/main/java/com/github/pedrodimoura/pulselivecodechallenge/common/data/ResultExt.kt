package com.github.pedrodimoura.pulselivecodechallenge.common.data

import com.github.pedrodimoura.pulselivecodechallenge.common.data.model.DefaultNetworkError
import com.github.pedrodimoura.pulselivecodechallenge.common.data.model.SessionExpiredException
import java.io.IOException

fun <T> Result<T>.ifThrowParseError(): T = getOrElse {
    when (it) {
        is IOException -> throw SessionExpiredException(it)
        else -> throw DefaultNetworkError(it)
    }
}