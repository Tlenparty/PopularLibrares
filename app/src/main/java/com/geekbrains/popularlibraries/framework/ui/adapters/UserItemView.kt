package com.geekbrains.popularlibraries.framework.ui.adapters

import com.geekbrains.popularlibraries.model.entites.GithubUser

/**
 * Интерфейс для позиции списка
 */
interface UserItemView:IItemView {
    fun setLogin(text:String)

    fun setAvatar(user: GithubUser)
}