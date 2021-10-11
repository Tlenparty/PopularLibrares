package com.geekbrains.popularlibraries.baselogic.user

import com.geekbrains.popularlibraries.baselogic.ExceptionView
import com.geekbrains.popularlibraries.model.entites.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserView: MvpView, ExceptionView {
    fun init()

    fun showUser(user: GithubUser)
    fun updateRepositoryList()
}