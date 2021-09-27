package com.geekbrains.popularlibraries.framework.ui.view.user_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.geekbrains.popularlibrares.databinding.FragmentUserBinding
import com.geekbrains.popularlibraries.framework.App.Companion.router
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepo
import com.geekbrains.popularlibraries.presentation.UserPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView {
    private lateinit var binding: FragmentUserBinding
    private val userLogin by lazy { arguments?.getString(USER_LOGIN) }
    private val presenter by moxyPresenter {
        UserPresenter(
            GithubUsersRepo(),
            router,
            userLogin
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val USER_LOGIN = "userLogin"
        fun newInstance(userLogin: String): Fragment = UserFragment()
            .also {
                it.arguments = bundleOf(USER_LOGIN to userLogin)
            }
    }

    override fun showUser(userLogin: String) {
        binding.userName.text = userLogin
    }
}




