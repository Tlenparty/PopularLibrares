package com.geekbrains.popularlibraries.di.modules

import com.geekbrains.popularlibraries.api.IGithubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private const val API_URL = "api_url"
    }

    @Named(API_URL)
    @Provides
    fun provideBaseUrl(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun provideGithubApi(@Named(API_URL) baseUrl: String): IGithubApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IGithubApi::class.java)

}