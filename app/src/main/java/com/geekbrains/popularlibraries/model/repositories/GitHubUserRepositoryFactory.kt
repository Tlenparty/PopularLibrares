package com.geekbrains.popularlibraries.model.repositories

import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository

object GitHubUserRepositoryFactory {
    fun create(): GithubUsersRepository = GithubUsersLocalRepositoryImpl()

}