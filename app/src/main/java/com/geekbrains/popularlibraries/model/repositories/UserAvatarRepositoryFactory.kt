package com.geekbrains.popularlibraries.model.repositories

import android.content.Context
import com.geekbrains.popularlibraries.helpers.network.NetworkStatusFactory

object UserAvatarRepositoryFactory {

    fun create(context: Context): UserAvatarRepository =
        UserAvatarRepositoryImpl(
            context,
            NetworkStatusFactory.create(context)
        )
}