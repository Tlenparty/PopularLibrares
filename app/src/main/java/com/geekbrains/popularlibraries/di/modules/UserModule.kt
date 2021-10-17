package com.geekbrains.popularlibraries.di.modules

import com.geekbrains.popularlibraries.converter.Converter
import com.geekbrains.popularlibraries.converter.ConverterImpl
import com.geekbrains.popularlibraries.helpers.network.INetworkStatus
import com.geekbrains.popularlibraries.helpers.network.NetworkStatusImpl
import com.geekbrains.popularlibraries.model.db.cache.GithubCacheImpl
import com.geekbrains.popularlibraries.model.db.cache.IGithubCache
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import com.geekbrains.popularlibraries.model.repositories.GithubUsersRepositoryImpl
import com.geekbrains.popularlibraries.model.repositories.UserAvatarRepository
import com.geekbrains.popularlibraries.model.repositories.UserAvatarRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

// Необходимо добавить в компоненты
@Module
interface UserModule {

    @Singleton
    @Binds // Указывает метот внутри модуля, набезе него надо сделать фабрику внутри которой буду зависимости
    fun bindGithubUsersRepository(githubUsersRepositoryImpl: GithubUsersRepositoryImpl): GithubUsersRepository

    @Singleton
    @Binds
    fun bindGithubCache(githubCache: GithubCacheImpl): IGithubCache

    @Singleton
    @Binds
    fun bindNetworkStatus(networkStatus: NetworkStatusImpl): INetworkStatus

    @Binds
    fun bindImageConverter(imageConverter: ConverterImpl): Converter

    @Singleton
    @Binds
    fun bindUserAvatarRepository(userAvatarRepository: UserAvatarRepositoryImpl): UserAvatarRepository


}