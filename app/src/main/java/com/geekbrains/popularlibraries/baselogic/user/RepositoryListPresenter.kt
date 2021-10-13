package com.geekbrains.popularlibraries.baselogic.user

import com.geekbrains.popularlibraries.baselogic.ListPresenter
import com.geekbrains.popularlibraries.baselogic.user_repositories.RepositoryItemView
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository

class RepositoryListPresenter : ListPresenter<RepositoryItemView> {
    val repositories = mutableListOf<GithubUserRepository>()

    override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

    override fun getCount() = repositories.size

    override fun bindView(view: RepositoryItemView) {
        val repository = repositories[view.currentPosition]
        view.setName(repository.fullName)
    }
}