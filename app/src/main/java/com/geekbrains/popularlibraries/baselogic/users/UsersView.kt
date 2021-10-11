package com.geekbrains.popularlibraries.baselogic.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    // Для первичной инициализайии списка при присоединении View к Presenter
    fun init()

    fun updateList()

    fun showException(throwable: Throwable)

}