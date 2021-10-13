package com.geekbrains.popularlibraries.baselogic.user

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.geekbrains.popularlibraries.baselogic.ExceptionView
import com.geekbrains.popularlibraries.model.entites.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserView : MvpView, ExceptionView {
    fun init()

    fun showUser(user: GithubUser, requestBuilder: RequestBuilder<Drawable>)
    fun updateRepositoryList()
}