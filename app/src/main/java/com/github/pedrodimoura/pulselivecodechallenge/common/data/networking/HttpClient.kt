package com.github.pedrodimoura.pulselivecodechallenge.common.data.networking

sealed interface HttpClient {
    interface RetrofitClient : HttpClient {
        fun <T> create(c: Class<T>): T
    }
}
