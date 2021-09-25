package com.geekbrains.popularlibraries.framework.ui.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.popularlibraries.framework.ui.view.users_fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UsersScreen:FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.newInstance()
    }
}