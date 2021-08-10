package com.github.pedrodimoura.pulselivecodechallenge.common.data.model

class SessionExpiredException(
    cause: Throwable? = null,
) : Throwable("Session has expired!", cause)
