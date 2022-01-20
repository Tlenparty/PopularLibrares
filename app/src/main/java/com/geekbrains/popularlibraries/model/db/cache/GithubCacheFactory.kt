package com.geekbrains.popularlibraries.model.db.cache

import com.geekbrains.popularlibraries.model.db.AppDB

object GithubCacheFactory {
    fun create(appDB: AppDB): IGithubCache = GithubCacheImpl(appDB)
}