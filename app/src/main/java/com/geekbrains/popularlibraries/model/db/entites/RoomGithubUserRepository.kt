package com.geekbrains.popularlibraries.model.db.entites

import androidx.room.*
import com.geekbrains.popularlibraries.model.db.AppDB
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = AppDB.TABLE_USER_REPOSITORIES,
    // Внешние ключи
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        // Родительская колонка
        parentColumns = [AppDB.USER_ID],
        // Дочерняя колонка
        childColumns = [AppDB.USER_ID],
        // Если табличка удаляется, удаляем каскадно
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubUserRepository(
    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @ColumnInfo(name = AppDB.USER_ID)
    var userId: Long,

    @SerializedName("name")
    @ColumnInfo(name = AppDB.NAME)
    var name: String,

    @SerializedName("full_name")
    @ColumnInfo(name = AppDB.FULL_NAME)
    val fullName: String,

    @SerializedName("forks")
    @ColumnInfo(name = AppDB.FORKS)
    val countForks: Int,

    @SerializedName("size")
    @ColumnInfo(name = AppDB.SIZE)
    val size: Int,

    @SerializedName("created_at")
    @ColumnInfo(name = AppDB.CREATED)
    val created: String
) {
    @Ignore
    var owner: GithubUserRepositoryOwner? = null
}