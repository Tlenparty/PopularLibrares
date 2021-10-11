package com.geekbrains.popularlibraries.model.repositories

import com.geekbrains.popularlibraries.api.GithubApiFactory
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
object GithubUsersRepositoryFactory {
    fun create(): GithubUsersRepository = GithubUsersRepositoryImpl(GithubApiFactory.create())
}