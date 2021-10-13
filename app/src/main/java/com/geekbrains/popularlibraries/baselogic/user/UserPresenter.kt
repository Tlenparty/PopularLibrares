package com.geekbrains.popularlibraries.baselogic.user

import com.geekbrains.popularlibraries.baselogic.BasePresenter
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulers
import com.geekbrains.popularlibraries.helpers.screens.RepositoryScreen
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import com.geekbrains.popularlibraries.model.repositories.RepositoryListPresenter
import com.geekbrains.popularlibraries.model.repositories.UserAvatarRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

class UserPresenter(
    private val userRepository: GithubUsersRepository,
    router: Router,
    private val userLogin: String?,
    private val appSchedulers: AppSchedulers,
    private val userAvatarRepository: UserAvatarRepository,
    val repositoryListPresenter: RepositoryListPresenter
) : BasePresenter<UserView>(router) {

    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        //подписываемся на поток пользователя
        userLogin?.let {
            //загружаем информацию о пользователе
            disposables += loadUserInfo(it)

            //загружаем информацию о репозиториях
            disposables += loadRepositoriesInfo(it)

            //обработчик выбора репозитория
            repositoryListPresenter.itemClickListener = { item ->
                val repositoryName = repositoryListPresenter.repositories[item.currentPosition].name
                router.navigateTo(RepositoryScreen(it, repositoryName))
            }

        } ?: let {
            viewState.showException(Throwable("Пустой логин пользователя"))
        }
    }

    override fun destroyView(view: UserView?) {
        disposable.dispose()
    }


    //загрузить информацию о репозиториях пользователя
    private fun loadRepositoriesInfo(login: String): Disposable {
        return userRepository
            .getRepositories(login)
            .observeOn(appSchedulers.main())
            .subscribeOn(appSchedulers.background()) //обработку делаем в отдельном потоке
            .subscribe(
                //уведомляем view о том, что получили информацию о репозиториях
                { repositories ->
                    repositoryListPresenter.repositories.addAll(repositories)
                    viewState.updateRepositoryList() },

                { exception -> viewState.showException(exception) }
            )
    }

    //загрузить информацию о пользователе
    private fun loadUserInfo(login: String): Disposable {
        return userRepository
            .getUser(login)
            .observeOn(appSchedulers.main())
            .subscribeOn(appSchedulers.background()) //обработку делаем в отдельном потоке
            .subscribe(
                //уведомляем view о том, что получили информацию о пользователе
                { gitHubUser ->
                    viewState.showUser(gitHubUser, userAvatarRepository.imageBuilder(gitHubUser))
                },

                { exception -> viewState.showException(exception) }
            )
    }

}
