package com.geekbrains.popularlibraries.framework.ui.view.users_fragment

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    // Для первичной инициализайии списка при присоединении View к Presenter
    fun init()

    fun updateList()

}