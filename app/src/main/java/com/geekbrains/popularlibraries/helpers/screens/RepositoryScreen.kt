package com.geekbrains.popularlibraries.helpers.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.geekbrains.popularlibraries.baselogic.user_repositories.RepositoryFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RepositoryScreen (private val userLogin: String,
                        private val repositoryName: String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment = RepositoryFragment.newInstance(userLogin, repositoryName)
}