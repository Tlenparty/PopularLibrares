package com.geekbrains.popularlibraries.baselogic.users

import com.geekbrains.popularlibraries.baselogic.BasePresenter
import com.geekbrains.popularlibraries.framework.ui.adapters.IUserListPresenter
import com.geekbrains.popularlibraries.framework.ui.adapters.UserItemView
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulers
import com.geekbrains.popularlibraries.helpers.screens.ImageCompressorScreen
import com.geekbrains.popularlibraries.helpers.screens.UserScreen
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxkotlin.plusAssign

class UsersPresenter(
    private val usersRepository: GithubUsersRepository,
    private val appSchedulers: AppSchedulers, router: Router
) : BasePresenter<UsersView>(router) {

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun bindView(view: UserItemView) {
            val user = users[view.currentPosition]
            view.setLogin(user.login)
            //добавим аватарку
            user.avatarUrl?.let {
                view.setAvatar(it)
            }
        }

        override fun getCount(): Int = users.size

    }

    val usersListPresenter = UserListPresenter()

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
        disposables +=
            usersRepository
                .getUsers()
                .observeOn(appSchedulers.main())
                .subscribeOn(appSchedulers.background()) //обработку делаем в отдельном потоке
                .subscribe(
                    { gitHubUsers ->
                        usersListPresenter.users.addAll(gitHubUsers)
                        viewState.updateList()
                    },
                    { exception -> viewState.showException(exception) }
                )

        //обработчик выбора логина из списка
        usersListPresenter.itemClickListener = {
            val userLogin = usersListPresenter.users[it.currentPosition].login
            router.navigateTo(UserScreen(userLogin))

        }
    }

    //обработка кнопки открытия окна с обработкой изображения
    fun openWinImageCompressor() {
        router.navigateTo(ImageCompressorScreen())
    }

    override fun destroyView(view: UsersView?) {
        disposables.dispose()
    }
}
