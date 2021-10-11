package com.geekbrains.popularlibraries.model.entites

import io.reactivex.Maybe
import io.reactivex.Single

interface GithubUsersRepository {
    //получить список пользователей
    fun getUsers(): Single<List<GithubUser>>

    //Получить конкретного пользователя
    fun getUser(userLogin: String): Single<GithubUser>

    //получить список репозиториев пользователя
    fun getRepositories(login: String): Single<List<GithubUserRepository>>

    //получить информацию о репозитории пользователя
    fun getRepository(login: String, repositoryName: String): Single<GithubUserRepository>
}