package com.geekbrains.popularlibraries.extentions

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition


//к тексту слева добавить закругленное изображение
@Suppress("IMPLICIT_CAST_TO_ANY")
fun TextView.setStartDrawableCircleImageFromUri(requestBuilder: RequestBuilder<Drawable>) {
    requestBuilder
        .apply(
            RequestOptions
                .circleCropTransform()
                .override(80.dp(this.context))
        )
        .into(object : CustomViewTarget<TextView, Drawable>(this) {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.setCompoundDrawable(resource, null, null, null)
            }
            override fun onResourceCleared(placeholder: Drawable?) {
                view.setCompoundDrawable(placeholder, null, null, null)
            }
            override fun onLoadFailed(errorDrawable: Drawable?) {
                view.setCompoundDrawable(errorDrawable, null, null, null)
            }
        })
}

fun TextView.setCompoundDrawable(
    left: Drawable? = null,
    top: Drawable? = null,
    right: Drawable? = null,
    bottom: Drawable? = null
) {
    setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
}