package com.github.pedrodimoura.pulselivecodechallenge.common.network.di

import android.util.Log
import com.github.pedrodimoura.pulselivecodechallenge.common.data.networking.HttpClient
import com.github.pedrodimoura.pulselivecodechallenge.common.data.networking.retrofit.RetrofitClientImpl
import com.github.pedrodimoura.pulselivecodechallenge.common.testUrl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkHiltModuleTest {

    private const val OK_HTTP_LOGGING_TAG = "ok_http_log_android_test"

    @Provides
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor {
            Log.i(OK_HTTP_LOGGING_TAG, it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesGsonConverterFactory(
        gson: Gson,
    ) = GsonConverterFactory.create(gson)

    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun providesRetrofitHttpClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): HttpClient.RetrofitClient = RetrofitClientImpl(testUrl, okHttpClient, gsonConverterFactory)
}
