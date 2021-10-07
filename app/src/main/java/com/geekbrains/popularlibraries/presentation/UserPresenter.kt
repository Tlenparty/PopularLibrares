package com.geekbrains.popularlibraries.presentation

import com.geekbrains.popularlibraries.framework.ui.view.user_fragment.UserView
import com.geekbrains.popularlibraries.model.repositories.GithubUsersLocalRepositoryImpl
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserPresenter(
    private val usersRepository: GithubUsersLocalRepositoryImpl,
    private val router: Router,
    private val userLogin: String?
) : MvpPresenter<UserView>() {

    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        userLogin?.let {
            disposable.add(
                usersRepository
                    .getUser(it)
                    .subscribe(
                        { githubUser -> viewState.showUser(githubUser.login) },
                        { exception -> viewState.showException(exception) }
                    )
            )
        }
    }

    override fun destroyView(view: UserView?) {
        disposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
