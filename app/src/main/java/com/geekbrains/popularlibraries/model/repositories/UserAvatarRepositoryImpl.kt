package com.geekbrains.popularlibraries.model.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.geekbrains.popularlibraries.helpers.FolderHelper
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.helpers.network.INetworkStatus
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

class UserAvatarRepositoryImpl(
    private val context: Context,
    private val networkStatus: INetworkStatus
) : UserAvatarRepository {

    override fun imageBuilder(user: GithubUser): RequestBuilder<Drawable> {
        val fileName = "avatar_${user.id}"
        val avatarFile = File(FolderHelper(context).imageFolder(), fileName)

        return networkStatus
            .isOnlineSingle()
            .subscribeOn(Schedulers.io()) //обработку делаем в отдельном потоке
            .map { internetEnable ->
                //если есть интернет, то тянем картинку из него, в противном случае - закешированную картинку
                if (internetEnable) {
                    Glide.with(context)
                        .load(user.avatarUrl)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean = false

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Timber.d("onResourceReady")
                                //закешируем фото
                                resource?.let {
                                    Timber.d("resource not null")
                                    FileOutputStream(avatarFile).use { fos ->
                                        it.toBitmap().compress(Bitmap.CompressFormat.JPEG, 80, fos)
                                        Timber.d("save resource in " + fileName)
                                    }
                                }
                                return false
                            }
                        })
                } else {
                    Glide.with(context)
                        .load(avatarFile)
                }
            }.blockingGet()
    }

}