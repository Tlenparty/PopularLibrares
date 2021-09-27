package com.geekbrains.popularlibraries.model.entites

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser>  = repositories

    fun getUser(userLogin: String): GithubUser {
        return repositories.first {
            it.login == userLogin
        }
    }
}