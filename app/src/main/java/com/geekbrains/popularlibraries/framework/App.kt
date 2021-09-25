package com.geekbrains.popularlibraries.framework

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var appInstance: App
        //todo с dagger это нужно изменить
        private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

        val navigatorHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router
    }
}
