package com.geekbrains.popularlibraries.di

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

// Реализация activity так, чтобы можно было использовать dagger и moxy, чтобы обойти множественное наследование
// HasAndroidInjector - чтобы модуль(android injection module) мог найти и запровайдить зависимости
abstract class BaseDaggerActivity(@LayoutRes contentLayoutId: Int = 0) :
    MvpAppCompatActivity(contentLayoutId),
    HasAndroidInjector {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}
