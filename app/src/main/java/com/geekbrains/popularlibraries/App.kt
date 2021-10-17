package com.geekbrains.popularlibraries

import android.app.Application
import com.geekbrains.popularlibraries.di.DaggerAppComponent
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulersImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

// DaggerApplication помогает в жизненном цикле и с сервисами
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .apply {
                withContext(applicationContext)
                val cicerone = Cicerone.create()
                withNavigationHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
                withAppScheduler(AppSchedulersImpl())
            }
            .build()
}

