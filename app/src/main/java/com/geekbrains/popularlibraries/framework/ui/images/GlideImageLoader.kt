package com.geekbrains.popularlibraries.framework.ui.images

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class GlideImageLoader : IImageLoader<ImageView> {

    @SuppressLint("CheckResult")
    override fun load(url: String, target: ImageView) {
        // С помощью with показываем куда хотим загрузить картинку в target(ImageView)
        Glide.with(target.context)
            .asBitmap() //Можно грузить картинку как Bitmap
            .load(url)
            //  .into(target)
            .listener(@SuppressLint("CheckResult")
            object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

            })

    }
}