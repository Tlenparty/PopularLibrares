package com.geekbrains.popularlibraries.model.repositories

import com.geekbrains.popularlibraries.api.IGithubApi
import com.geekbrains.popularlibraries.helpers.network.INetworkStatus
import com.geekbrains.popularlibraries.model.db.cache.IGithubCache
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import io.reactivex.Single

class GithubUsersRepositoryImpl(
    private val githubApi: IGithubApi,
    private val githubCache: IGithubCache,
    private val networkStatus: INetworkStatus
) : GithubUsersRepository {

    override fun getUsers(): Single<List<GithubUser>> =
        //если есть интернет, то тянем данные из него, в противном случае - закешированные из базы
        networkStatus
            .isOnlineSingle()
            // На каждую смену онлайна
            .flatMap { internetEnable ->
                if (internetEnable) {
                    githubApi
                        //получаем пользователей из интернета
                        .getUsers()
                        //Maps the success value of the upstream Single into
                        // an Iterable and emits its items as an Observable sequence.
                        .flattenAsObservable { users ->
                            //закешируем полученных пользователей
                            githubCache.insertUsers(users = users)
                            return@flattenAsObservable users
                        }
                        .toList()
                } else {
                    githubCache.getUsers()
                }
            }.doOnDispose { networkStatus.unBind() }

    override fun getUser(userLogin: String): Single<GithubUser> =
        //если есть интернет, то тянем данные из него, в противном случае - закешированные из базы
        networkStatus
            .isOnlineSingle()
            .flatMap { internetEnable ->
                if (internetEnable) {
                    githubApi
                        //получаем пользователя из интернета
                        .getUser(userLogin)
                        .map { user ->
                            //закешируем полученного пользователя
                            githubCache.insertUsers(users = listOf(user))
                            return@map user
                        }
                } else {
                    githubCache.getUser(userLogin)
                }
            }
            .doOnDispose { networkStatus.unBind() }

    override fun getRepositories(login: String): Single<List<GithubUserRepository>> =
        networkStatus
            .isOnlineSingle()
            .flatMap { internetEnable ->
                if (internetEnable) {
                    githubApi
                        .getRepositories(login)
                        .map { users ->
                            //проставим id пользователя в отдельную переменную для дальнейшей работы
                            users.forEach { user -> user.userId = user.owner?.userId ?: 0 }
                            return@map users
                        }
                        .flattenAsObservable { repositories ->
                            //закешируем полученный репозиторий
                            githubCache.insertRepositories(repositories)
                            return@flattenAsObservable repositories
                        }
                        .toList()
                } else {
                    githubCache.getRepositoriesOnUserLogin(login)
                }
            }.doOnDispose { networkStatus.unBind() }

    override fun getRepository(
        login: String,
        repositoryName: String
    ): Single<GithubUserRepository> =
        //если есть интернет, то тянем данные из него, в противном случае - закешированные из базы
        networkStatus
            .isOnlineSingle()
            .flatMap { internetEnable ->
                if (internetEnable) {
                    githubApi
                        .getRepository(login, repositoryName)
                        //проставим id пользователя в отдельную переменную для дальнейшей работы
                        .map { user ->
                            user.userId = user.owner?.userId ?: 0
                            //закешируем полученный репозиторий
                            githubCache.insertRepository(user)
                            return@map user
                        }
                } else {
                    githubCache.getRepositoryOnUserLogin(login, repositoryName)
                }
            }.doOnDispose { networkStatus.unBind() }
}
