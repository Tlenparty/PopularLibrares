package com.geekbrains.popularlibraries.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
// ApiHolder
object GithubApiFactory {
    //REST и RPC разобраться что это
    private val BASE_URL = "https://api.github.com"
    // Фабрика для провайда зависимости
    fun create(): GithubApi =
        // Шаблон строитель
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder() // Для встраивания интерцепторов
                    // Интерцепоторы встраиваются в цепочку обработки запрос - ответ
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            )
            // Для конвертации ответов с сервера в реактив
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // Для конвертации GSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java) // Сюда передается сервис который хотим использовать
}