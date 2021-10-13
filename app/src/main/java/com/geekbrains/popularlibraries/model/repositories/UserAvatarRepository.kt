package com.geekbrains.popularlibraries.model.repositories

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.geekbrains.popularlibraries.model.entites.GithubUser

interface UserAvatarRepository {

    //сформировать изображение
    fun imageBuilder(user: GithubUser): RequestBuilder<Drawable>
}