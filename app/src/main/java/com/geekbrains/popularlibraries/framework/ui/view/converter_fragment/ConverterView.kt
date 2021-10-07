package com.geekbrains.popularlibraries.framework.ui.view.converter_fragment

import android.net.Uri
import com.geekbrains.popularlibraries.framework.ui.view.ScreenView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ConverterView : ScreenView {

    @AddToEndSingle
    fun showContent(uri: Uri?)

    @AddToEndSingle
    fun showLoading()
}