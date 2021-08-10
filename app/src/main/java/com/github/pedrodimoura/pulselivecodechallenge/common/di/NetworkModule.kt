package com.github.pedrodimoura.pulselivecodechallenge.common.di

import android.util.Log
import com.github.pedrodimoura.pulselivecodechallenge.BuildConfig
import com.github.pedrodimoura.pulselivecodechallenge.common.data.networking.HttpClient
import com.github.pedrodimoura.pulselivecodechallenge.common.data.networking.retrofit.RetrofitClientImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

private const val OK_HTTP_LOGGING_TAG = "ok_http_log"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesGsonConverterFactory(
        gson: Gson,
    ) = GsonConverterFactory.create(gson)

    @Provides
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor {
            Log.i(OK_HTTP_LOGGING_TAG, it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun providesOkHttpClient(
        interceptor: Interceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG)
            okHttpClient.addInterceptor(interceptor)

        return okHttpClient.build()
    }

    @Provides
    fun providesRetrofitClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): HttpClient.RetrofitClient =
        RetrofitClientImpl(BuildConfig.BASE_URL, okHttpClient, gsonConverterFactory)
}
