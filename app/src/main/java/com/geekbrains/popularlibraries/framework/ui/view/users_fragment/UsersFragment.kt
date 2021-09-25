package com.geekbrains.popularlibraries.framework.ui.view.users_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.popularlibrares.databinding.FragmentUsersBinding
import com.geekbrains.popularlibraries.framework.App.Companion.router
import com.geekbrains.popularlibraries.framework.ui.adapters.UsersRVAdapter
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepo
import com.geekbrains.popularlibraries.presentation.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment: MvpAppCompatFragment(), UsersView{
    private lateinit var vb: FragmentUsersBinding
    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), router) }
    private var adapter: UsersRVAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  = FragmentUsersBinding.inflate(inflater,container,false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

}


