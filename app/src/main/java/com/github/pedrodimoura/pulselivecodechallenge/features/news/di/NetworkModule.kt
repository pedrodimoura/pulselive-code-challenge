package com.github.pedrodimoura.pulselivecodechallenge.features.news.di

import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.NewsRemoteDatasource
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.impl.NewsRemoteDatasourceImpl
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service.NewsService
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.repository.NewsRepositoryImpl
import com.github.pedrodimoura.pulselivecodechallenge.features.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): NewsRepository

    @Binds
    abstract fun providesNewsRemoteDatasource(
        newsRemoteDatasourceImpl: NewsRemoteDatasourceImpl,
    ): NewsRemoteDatasource

    @Module
    @InstallIn(SingletonComponent::class)
    class ServiceModule {
        @Provides
        fun providesNewsService(
            retrofit: Retrofit,
        ): NewsService = retrofit.create(NewsService::class.java)
    }
}
