package com.geekbrains.popularlibraries.model.db.cache

import com.geekbrains.popularlibraries.model.db.AppDB
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GithubCacheImpl @Inject constructor(private val appDB: AppDB):IGithubCache {

    //добавить пользователей в базу
    override fun insertUsers(users: List<GithubUser>): Completable =
        appDB.githubUserDao().insert(users)


    //получить список пользователей
    override fun getUsers(): Single<List<GithubUser>> = appDB.githubUserDao().getUsers()

    //получить конкретного пользователя
    override fun getUser(userLogin: String): Single<GithubUser> = appDB.githubUserDao().getUser(userLogin)

    //добавить репозиторий в базу
    override fun insertRepository(repository: GithubUserRepository): Completable =
        appDB.githubUserRepositoryDao().insert(listOf(repository))


    //добавить репозитории в базу
    override fun insertRepositories(repositories: List<GithubUserRepository>): Completable =
        appDB.githubUserRepositoryDao().insert(repositories)


    //получить список репозиториев по логину пользователя
    override fun getRepositoriesOnUserLogin(userLogin: String): Single<List<GithubUserRepository>> =
        appDB.githubUserRepositoryDao().getRepositories(userLogin)

    //получить репозиторий по логину пользователя
    override fun getRepositoryOnUserLogin(userLogin: String, repositoryName: String): Single<GithubUserRepository> =
        appDB.githubUserRepositoryDao().getRepositoryOnUserLogin(userLogin, repositoryName)
}