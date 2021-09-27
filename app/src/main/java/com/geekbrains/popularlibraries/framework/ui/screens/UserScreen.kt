package com.geekbrains.popularlibraries.framework.ui.screens

import androidx.fragment.app.FragmentFactory
import com.geekbrains.popularlibraries.framework.ui.view.user_fragment.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen(private val userLogin:String):FragmentScreen {
    override fun createFragment(factory: FragmentFactory) = UserFragment.newInstance(userLogin)

}
