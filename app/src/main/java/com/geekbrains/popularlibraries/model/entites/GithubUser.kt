package com.geekbrains.popularlibraries.model.entites

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
//@SerializedName показывает как поле должно называеться  при сериализауции в скобках
//@Expose говорит что это поле надо сериализовать
data class GithubUser(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("login")
    var login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null
)