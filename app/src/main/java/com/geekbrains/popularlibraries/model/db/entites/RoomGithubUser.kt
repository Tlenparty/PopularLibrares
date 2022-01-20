package com.geekbrains.popularlibraries.model.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.geekbrains.popularlibraries.model.db.AppDB
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = AppDB.TABLE_USERS,
    indices = [Index(AppDB.LOGIN)]
)
data class RoomGithubUser(
    @PrimaryKey
    @ColumnInfo(name = AppDB.USER_ID)
    @SerializedName("id")
    val id: Long,

    @SerializedName("login")
    @ColumnInfo(name = AppDB.LOGIN)
    var login: String,

    @SerializedName("avatar_url")
    @ColumnInfo(name = AppDB.AVATAR_URL)
    val avatarUrl: String? = null

)