package com.geekbrains.popularlibraries.baselogic.user_repositories

import com.geekbrains.popularlibraries.baselogic.ExceptionView
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface RepositoryView: MvpView,ExceptionView {

    fun showInfo(text: String)
}