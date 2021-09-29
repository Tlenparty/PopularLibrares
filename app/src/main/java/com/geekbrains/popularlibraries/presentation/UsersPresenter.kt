package com.geekbrains.popularlibraries.presentation

import com.geekbrains.popularlibraries.framework.ui.adapters.IUserListPresenter
import com.geekbrains.popularlibraries.framework.ui.adapters.UserItemView
import com.geekbrains.popularlibraries.framework.ui.screens.UserScreen
import com.geekbrains.popularlibraries.framework.ui.view.users_fragment.UsersView
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.repositories.GithubUsersLocalRepositoryImpl
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepository: GithubUsersLocalRepositoryImpl,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun bindView(view: UserItemView) {
            val user = users[view.currentPosition]
            view.setLogin(user.login)
        }

        override fun getCount(): Int = users.size

    }

    val usersListPresenter = UserListPresenter()
    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
            val userLogin = usersListPresenter.users[it.currentPosition].login
            router.navigateTo(UserScreen(userLogin))
        }
    }

    private fun loadData() {
        // подписываемся на поток списка пользователей
        disposable.add(
            usersRepository
                .getUsers()
                .subscribe(
                    { gitHubUser -> usersListPresenter.users.addAll(gitHubUser) },
                    { exception -> viewState.showException(exception) }
                ))
        viewState.updateList()
    }

    override fun destroyView(view: UsersView?) {
        disposable.dispose()
    }
}