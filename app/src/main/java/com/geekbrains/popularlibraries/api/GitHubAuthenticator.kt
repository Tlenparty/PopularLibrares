package com.geekbrains.popularlibraries.api

import com.geekbrains.popularlibrares.BuildConfig
import okhttp3.*

object GitHubAuthenticator : Authenticator {
    // Реакция на 401 ошибку (О необходимости авторизоваться)
    override fun authenticate(route: Route?, response: Response): Request? {
        // Формируем новый запрос
        return response.request.newBuilder()
            .header(
                "Authorization", Credentials.basic(
                    "kek","lol"
                    /*BuildConfig.GITHUB_USER_NAME,
                    BuildConfig.GITHUB_USER_PASSWORD,*/
                )
            )
            .build()
    }


}