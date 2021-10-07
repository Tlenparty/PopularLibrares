package com.geekbrains.popularlibraries.model.repositories

import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

class GithubUsersLocalRepositoryImpl : GithubUsersRepository, @NonNull Disposable {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    override fun getUsers(): Single<List<GithubUser>> = Single.just(repositories)

    override fun getUser(userLogin: String): Maybe<GithubUser> {
        val userGithub: GithubUser? = repositories.firstOrNull{
            user->user.login == userLogin
        }
        return Maybe.defer {
            userGithub?.let {
                return@defer Maybe.just(it)
            }
            return@defer Maybe.error(Throwable("Не найден пользователь по переданному логину"))
        }
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

}