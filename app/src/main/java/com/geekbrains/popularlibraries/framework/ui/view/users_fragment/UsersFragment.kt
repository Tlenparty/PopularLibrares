package com.geekbrains.popularlibraries.framework.ui.view.users_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.popularlibrares.databinding.FragmentUsersBinding
import com.geekbrains.popularlibraries.PopularLibraries.Navigation.router
import com.geekbrains.popularlibraries.extentions.showToast
import com.geekbrains.popularlibraries.framework.ui.adapters.UsersRVAdapter
import com.geekbrains.popularlibraries.model.repositories.GithubUsersLocalRepositoryImpl
import com.geekbrains.popularlibraries.presentation.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment: MvpAppCompatFragment(), UsersView{
    private lateinit var vb: FragmentUsersBinding
    private val presenter by moxyPresenter { UsersPresenter(GithubUsersLocalRepositoryImpl(), router) }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  = FragmentUsersBinding.inflate(inflater,container,false).also {
        vb = it
    }.root

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showException(throwable: Throwable) {
        context?.showToast(throwable.message)
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

}


