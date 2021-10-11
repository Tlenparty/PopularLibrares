package com.geekbrains.popularlibraries.helpers.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.popularlibraries.converter.ConverterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ConverterScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ConverterFragment.newInstance()

}