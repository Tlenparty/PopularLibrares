package com.geekbrains.popularlibraries.framework.ui.view.main_activity

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView