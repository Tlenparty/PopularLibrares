package com.geekbrains.popularlibraries.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
// Моджем встроиться в цепочку обработки
object GitHubApiInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request() // получаем запрос
                .newBuilder()  // создаем новый билдер чтобы на основе старого запроса создать новый

                //Авторизация ч/з заголовок Auth..n и базовую авторизацию BuildConfig.GITHUB_USER_PASSWORD
            //    .head("Authoirization",Credentials.basic(GITHUB_USER_NAME,USER_PASSWORD))
                .build()
        )

}