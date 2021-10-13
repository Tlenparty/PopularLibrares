package com.geekbrains.popularlibraries.model.db.cache

import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import io.reactivex.Single

interface IGithubCache {

    //добавить пользователей в базу
    fun insertUsers(users: List<GithubUser>)

    //получить список пользователей
    fun getUsers(): Single<List<GithubUser>>

    //получить конкретного пользователя
    fun getUser(userLogin: String): Single<GithubUser>

    //добавить репозиторий в базу
    fun insertRepository(repository: GithubUserRepository)

    //добавить репозитории в базу
    fun insertRepositories(repositories: List<GithubUserRepository>)

    //получить список репозиториев по логину пользователя
    fun getRepositoriesOnUserLogin(userLogin: String): Single<List<GithubUserRepository>>

    //получить репозиторий по логину пользователя
    fun getRepositoryOnUserLogin(userLogin: String, repositoryName: String): Single<GithubUserRepository>
}