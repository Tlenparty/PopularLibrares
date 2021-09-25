package com.geekbrains.popularlibraries.framework.ui.view.main_activity

import android.os.Bundle
import com.geekbrains.popularlibrares.R
import com.geekbrains.popularlibrares.databinding.ActivityMainBinding
import com.geekbrains.popularlibraries.framework.ui.view.main_activity.MainView
import com.geekbrains.popularlibraries.framework.App.Companion.navigatorHolder
import com.geekbrains.popularlibraries.framework.App.Companion.router
import com.geekbrains.popularlibraries.framework.ui.screens.UsersScreen
import com.geekbrains.popularlibraries.presentation.MainPresenter
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(router) }
    private val navigator = AppNavigator(this, R.id.screen_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}