package com.geekbrains.popularlibraries.baselogic.user_repositories

import com.geekbrains.popularlibraries.baselogic.BasePresenter
import com.geekbrains.popularlibraries.helpers.scheduler.AppSchedulers
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

class RepositoryPresenter(
    private val userRepository: GithubUsersRepository,
    private val appSchedulers: AppSchedulers,
    router: Router,
    private val userLogin: String?,
    private val repositoryName: String?
) : BasePresenter<RepositoryView>(router) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        //загружаем информацию о репозитории
        userLogin?.let { login ->
            repositoryName?.let { repository ->
                disposables += loadRepositoriesInfo(login, repository)
            }
        }
    }

    //загрузить информацию о репозиториях пользователя
    private fun loadRepositoriesInfo(loin: String, repository: String): Disposable {
        return userRepository
            .getRepository(loin, repository)
            .map { repo ->
                val data = StringBuilder()
                data.append("Имя репозитория: ${repo.name}\n")
                    .append("Полное имя: ${repo.fullName}\n")
                    .append("Кол-во форков: ${repo.countForks}\n")
                    .append("Размер: ${repo.size}\n")
                    .append("Дата создания: ${repo.created}\n")

                data.toString()
            }
            .observeOn(appSchedulers.main())
            .subscribeOn(appSchedulers.background()) //обработку делаем в отдельном потоке
            .subscribe(
                { repositoryInfo -> viewState.showInfo(repositoryInfo) },
                { exception -> viewState.showException(exception) }
            )
    }
}