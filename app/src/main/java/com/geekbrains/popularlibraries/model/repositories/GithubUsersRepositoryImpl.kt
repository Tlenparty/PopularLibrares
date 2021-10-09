package com.geekbrains.popularlibraries.model.repositories

import com.geekbrains.popularlibraries.api.GithubApi
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import io.reactivex.Single

class GithubUsersRepositoryImpl(private val githubApi: GithubApi) : GithubUsersRepository {
    override fun getUsers(): Single<List<GithubUser>> = githubApi.getUsers()

    override fun getUser(userLogin: String): Single<GithubUser> = githubApi.getUser(userLogin)

    override fun getRepositories(login: String): Single<List<GithubUserRepository>> =
        githubApi.getRepositories(login)

    override fun getRepository(
        login: String,
        repositoryName: String
    ): Single<GithubUserRepository> =
        githubApi.getRepository(login, repositoryName)
}