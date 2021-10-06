package com.geekbrains.popularlibraries.framework.ui.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.popularlibraries.framework.ui.view.converter_fragment.ConverterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ConverterScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ConverterFragment.newInstance()

}