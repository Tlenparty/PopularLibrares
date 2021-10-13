package com.geekbrains.popularlibraries.baselogic.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geekbrains.popularlibrares.databinding.FragmentUsersBinding
import com.geekbrains.popularlibraries.PopularLibraries.Navigation.router
import com.geekbrains.popularlibraries.extentions.showToast
import com.geekbrains.popularlibraries.framework.ui.images.GlideImageLoader
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulerFactory
import com.geekbrains.popularlibraries.model.repositories.GithubUsersRepositoryFactory
import com.geekbrains.popularlibraries.model.repositories.UserAvatarRepositoryFactory
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView {

    private val binding: FragmentUsersBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepositoryFactory.create(requireContext()),
            AppSchedulerFactory.create(),
            UserAvatarRepositoryFactory.create(requireContext()),
            router
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initListeners()
        return binding.root
    }

    override fun init() = with(binding) {
        rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(
            presenter.usersListPresenter,
            //  GlideImageLoader()
            UserAvatarRepositoryFactory.create(requireContext())
        )
        rvUsers.adapter = adapter
        rvUsers.itemAnimator = DefaultItemAnimator()
        rvUsers.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showException(throwable: Throwable) {
        context?.showToast(throwable.message)
    }

    //обработчики кнопок
    private fun initListeners() {
        binding.fabPicture.setOnClickListener { presenter.openWinImageCompressor() }
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

}


