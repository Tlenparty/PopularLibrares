package com.geekbrains.popularlibraries.di

import android.content.Context
import androidx.annotation.LayoutRes
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulers
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import javax.inject.Inject

//реализация fragment так, чтобы можно было использовать dagger и moxy
abstract class BaseDaggerFragment(@LayoutRes contentLayoutId: Int = 0) :
    MvpAppCompatFragment(contentLayoutId),
    HasAndroidInjector {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appSchedulers: AppSchedulers

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
