package com.geekbrains.popularlibraries.di.modules

import com.geekbrains.popularlibraries.baselogic.main.MainActivity
import com.geekbrains.popularlibraries.baselogic.user.UserFragment
import com.geekbrains.popularlibraries.baselogic.user_repositories.RepositoryFragment
import com.geekbrains.popularlibraries.baselogic.users.UsersFragment
import com.geekbrains.popularlibraries.converter.ConverterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UIModule {
    // Чтобы не было ошибки no injector factory bound for Class <...MainActivity>
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindRepositoryFragment(): RepositoryFragment

    @ContributesAndroidInjector
    fun bindConverterFragment(): ConverterFragment

}