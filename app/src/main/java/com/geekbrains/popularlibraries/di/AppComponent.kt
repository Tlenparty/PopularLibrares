package com.geekbrains.popularlibraries.di

import android.content.Context
import com.geekbrains.popularlibraries.App
import com.geekbrains.popularlibraries.di.modules.ApiModule
import com.geekbrains.popularlibraries.di.modules.DBModule
import com.geekbrains.popularlibraries.di.modules.UIModule
import com.geekbrains.popularlibraries.di.modules.UserModule
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulers
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

// Компонент - часть системы, класса, часть приложения в которую предоставляем зависимости
// Builder
// Android injector для объяснения как провайдить зависимости App
// module - сущность которая описывает некую часть графа зависимостей
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    UserModule::class,
    ApiModule::class,
    DBModule::class,
    UIModule::class])
interface AppComponent : AndroidInjector<App> {

    // Строитель компонента
    @Component.Builder
    interface Builder {

        //это singleton-зависимости для нашего компонента
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withAppScheduler(appSchedulers: AppSchedulers): Builder

        fun build(): AppComponent
    }
}