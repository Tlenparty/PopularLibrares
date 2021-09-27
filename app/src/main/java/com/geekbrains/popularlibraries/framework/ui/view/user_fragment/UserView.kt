package com.geekbrains.popularlibraries.framework.ui.view.user_fragment

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserView:MvpView {
    fun showUser(userLogin: String)
}