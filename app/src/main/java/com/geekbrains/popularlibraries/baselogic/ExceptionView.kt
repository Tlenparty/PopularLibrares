package com.geekbrains.popularlibraries.baselogic

import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface ExceptionView {
    fun showException(throwable: Throwable)
}