package com.geekbrains.popularlibraries.framework.ui.view.main_activity

import android.os.Bundle
import android.widget.Toast
import com.geekbrains.popularlibrares.R
import com.geekbrains.popularlibrares.databinding.ActivityMainBinding
import com.geekbrains.popularlibraries.PopularLibraries.Navigation.navigatorHolder
import com.geekbrains.popularlibraries.PopularLibraries.Navigation.router
import com.geekbrains.popularlibraries.framework.ui.view.main_activity.MainView
import com.geekbrains.popularlibraries.framework.ui.screens.ConverterScreen
import com.geekbrains.popularlibraries.framework.ui.screens.UsersScreen
import com.geekbrains.popularlibraries.model.network.NetworkState
import com.geekbrains.popularlibraries.model.network.NetworkStateObservable
import com.geekbrains.popularlibraries.presentation.MainPresenter
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.util.HalfSerializer.onNext
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.util.concurrent.TimeUnit

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, android.R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(ConverterScreen)

        val connect =
            NetworkStateObservable(this)
                .doOnNext { onNext(0, it) }
                .publish()

        connect.connect()

        disposables +=
            connect.delay(32L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe { onNext(1, it) }
        disposables += connect.delay(16L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe { onNext(2, it) }
        disposables += connect.delay(8L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe { onNext(3, it) }
    }

    private fun onNext(no: Int, state: NetworkState) {
        Toast.makeText(this, "$no: NetworkState: $state", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}