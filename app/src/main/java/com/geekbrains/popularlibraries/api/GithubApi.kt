package com.geekbrains.popularlibraries.api

import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

// Абсстрактный слой над данными над (Api)
interface GithubApi {
    //получить список пользователей
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    //получить конкретного пользователя
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

    //получить список репозиториев пользователя
    @GET("users/{login}/repos")
    fun getRepositories(@Path("login") login: String): Single<List<GithubUserRepository>>

    //получить список репозиториев пользователя
    @GET("repos/{login}/{repository}")
    fun getRepository(
        @Path("login") login: String,
        @Path("repository") repository: String,
    ): Single<GithubUserRepository>
}
