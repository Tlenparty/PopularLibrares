package com.geekbrains.popularlibraries.model.repositories

import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import io.reactivex.Single

class GithubUsersLocalRepositoryImpl : GithubUsersRepository {
    private val repositories = listOf(
        GithubUser(id = 1, login = "login1"),
        GithubUser(id = 2, login = "login2"),
        GithubUser(id = 3, login = "login3"),
        GithubUser(id = 4, login = "login4"),
        GithubUser(id = 5, login = "login5")
    )

    override fun getUsers(): Single<List<GithubUser>> =
        Single.fromCallable {
            //тут можно делать какую-то доп.обработку
            repositories
        }

    override fun getUser(userLogin: String): Single<GithubUser> {
        val userGithub: GithubUser? = repositories.firstOrNull() {
                user -> user.login == userLogin
        }
        return userGithub?.let {
            Single.just(it)
        } ?: Single.error(Throwable("Не найден пользователь по переданному логину"))
    }

    override fun getRepositories(login: String): Single<List<GithubUserRepository>> =
        Single.just(emptyList())

    override fun getRepository(
        login: String,
        repositoryName: String
    ): Single<GithubUserRepository> =
        Single.just(GithubUserRepository(
            id = 1,
            name = "name repository",
            fullName = "full name repository",
            countForks = 0,
            size = 100,
            created = "2021-08-11",
            userId = 1
        ))

}