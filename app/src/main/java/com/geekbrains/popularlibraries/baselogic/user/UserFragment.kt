package com.geekbrains.popularlibraries.baselogic.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geekbrains.popularlibrares.databinding.FragmentUserBinding
import com.geekbrains.popularlibraries.PopularLibraries.Navigation.router
import com.geekbrains.popularlibraries.baselogic.user_repositories.RepositoryRVAdapter
import com.geekbrains.popularlibraries.extentions.dp
import com.geekbrains.popularlibraries.extentions.showToast
import com.geekbrains.popularlibraries.baselogic.BackButtonListener
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulerFactory
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.repositories.GithubUsersRepositoryFactory
import com.geekbrains.popularlibraries.model.repositories.RepositoryListPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private lateinit var binding: FragmentUserBinding
    private val userLogin by lazy { arguments?.getString(USER_LOGIN) }
    private var adapter: RepositoryRVAdapter? = null
    private val presenter by moxyPresenter {
        UserPresenter(
            GithubUsersRepositoryFactory.create(),
            router,
            userLogin,
            AppSchedulerFactory.create(),
            RepositoryListPresenter()
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

    override fun init() = with(binding) {
        recyclerRepo.layoutManager = LinearLayoutManager(context)
        adapter = RepositoryRVAdapter(presenter.repositoryListPresenter)
        recyclerRepo.adapter = adapter
        recyclerRepo.itemAnimator = DefaultItemAnimator()
        recyclerRepo.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    override fun showUser(user: GithubUser) {
        binding.loginText.text = user.login

        //загружаем аватарку
        Glide.with(requireContext())
            .load(user.avatarUrl)
            .apply(
                RequestOptions
                    .circleCropTransform()
                    .override(140.dp(requireContext()))
            )
            .into(binding.avatarId)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateRepositoryList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showException(throwable: Throwable) {
        context?.showToast(throwable.message)
    }

    override fun backPressed() = presenter.backPressed()
}




