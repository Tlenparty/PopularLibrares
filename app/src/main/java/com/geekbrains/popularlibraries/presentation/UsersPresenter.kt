package com.geekbrains.popularlibraries.presentation

import com.geekbrains.popularlibraries.framework.ui.adapters.IUserListPresenter
import com.geekbrains.popularlibraries.framework.ui.adapters.UserItemView
import com.geekbrains.popularlibraries.framework.ui.view.users_fragment.UsersView
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {

    class UserListPresenter: IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount(): Int = users.size

    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
        }
    }

    private fun loadData(){
        // получение данных из репозитория
        val users = usersRepo.getUsers()
        // отдаем данные презентеру списка
        usersListPresenter.users.addAll(users)
        // командуем View обновить спискок
        viewState.updateList()
    }
}