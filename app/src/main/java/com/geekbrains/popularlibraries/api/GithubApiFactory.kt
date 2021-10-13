package com.geekbrains.popularlibraries.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// ApiHolder
object GithubApiFactory {
    //REST и RPC разобраться что это
    private val BASE_URL = "https://api.github.com"

    private val gson: Gson =
        GsonBuilder()
            .create()


    // Фабрика для провайда зависимости
    fun create(): IGithubApi =
        // Шаблон строитель
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder() // Для встраивания интерцепторов
                    // Для перехвата 401(треб авторизац) ошибки и для повторной авторизации
                    //.authenticator(GitHubAuthenticator)
                    // .authenticator { route, response -> response.request.newBuilder().build() }
                    //.addInterceptor(GitHubApiInterceptor)
                    // Интерцепоторы встраиваются в цепочку обработки запрос - ответ
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            // Для конвертации ответов с сервера в реактив
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // Для конвертации GSON
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IGithubApi::class.java) // Сюда передается сервис который хотим использовать
}