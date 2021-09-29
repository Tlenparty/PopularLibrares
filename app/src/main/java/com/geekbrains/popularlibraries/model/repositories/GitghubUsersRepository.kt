package com.geekbrains.popularlibraries.model.entites

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

interface GithubUsersRepository {

    fun getUsers(): Single<List<GithubUser>>
    fun getUser(userLogin: String):Maybe<GithubUser>
}