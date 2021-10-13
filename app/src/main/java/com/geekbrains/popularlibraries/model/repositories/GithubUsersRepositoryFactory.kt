package com.geekbrains.popularlibraries.model.repositories

import android.content.Context
import com.geekbrains.popularlibraries.api.GithubApiFactory
import com.geekbrains.popularlibraries.helpers.network.NetworkStatusFactory
import com.geekbrains.popularlibraries.model.db.AppDBFactory
import com.geekbrains.popularlibraries.model.db.cache.GithubCacheFactory
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository

object GithubUsersRepositoryFactory {
    fun create(context: Context): GithubUsersRepository = GithubUsersRepositoryImpl(
        GithubApiFactory.create(),
        GithubCacheFactory.create(AppDBFactory.create(context)),
        NetworkStatusFactory.create(context)
    )
}