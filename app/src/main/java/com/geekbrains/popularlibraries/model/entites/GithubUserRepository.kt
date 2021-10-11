package com.geekbrains.popularlibraries.model.entites

import com.google.gson.annotations.SerializedName

data class GithubUserRepository(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("forks")
    val countForks: Int,

    @SerializedName("size")
    val size: Int,

    @SerializedName("created_at")
    val created: String
)