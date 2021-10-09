package com.geekbrains.popularlibraries.baselogic.main

import android.annotation.SuppressLint
import android.util.Log
import com.geekbrains.popularlibraries.helpers.screens.UsersScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import moxy.MvpPresenter
import java.util.*
import java.util.concurrent.TimeUnit

class MainPresenter(val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen())
    }

    @SuppressLint("CheckResult")
    fun execExampleMap() {
        Observable.fromIterable(listOf("1", "2", "3", "4", "5"))
            .switchMap {
                val delay = Random().nextInt(1000).toLong()
                return@switchMap Observable.just(it + "x").delay(delay,
                    TimeUnit.MILLISECONDS)
            }
            .subscribe(
                { s -> Log.d("My", "onNext: $s") },
                { Log.d("My", "onError: ${it.message}") }
            )
    }

    fun backPressed() {
        router.exit()
    }

}