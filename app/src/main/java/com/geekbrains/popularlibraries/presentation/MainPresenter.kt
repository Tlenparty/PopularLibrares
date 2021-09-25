package com.geekbrains.popularlibraries.presentation

import com.geekbrains.popularlibraries.framework.ui.view.main_activity.MainView
import com.geekbrains.popularlibraries.framework.ui.screens.UsersScreen
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }

}