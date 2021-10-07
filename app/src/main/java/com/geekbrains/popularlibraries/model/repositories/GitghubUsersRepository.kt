package com.geekbrains.popularlibraries.model.entites

import io.reactivex.Maybe
import io.reactivex.Single


interface GithubUsersRepository {

    fun getUsers(): Single<List<GithubUser>>
    fun getUser(userLogin: String): Maybe<GithubUser>
}