package com.geekbrains.popularlibraries.model.entites

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.geekbrains.popularlibraries.model.db.AppDB
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@SerializedName показывает как поле должно называеться при сериализауции в скобках retr
//@Expose говорит что это поле надо сериализовать retr
//@Transient исключение полей при (де)сериализации (работает с room) retr

@Entity(
    tableName = AppDB.TABLE_USERS,
    indices = [Index(AppDB.LOGIN)]
)
data class GithubUser(
    @SerializedName("id") //("id", alternate=["anotherId"]) можем некоторые альтернативы иметь
    @ColumnInfo(name = AppDB.USER_ID)
    @PrimaryKey
    val id: Long,

    @SerializedName("login")
    @ColumnInfo(name = AppDB.LOGIN)
    var login: String,

    @SerializedName("avatar_url")
    @ColumnInfo(name = AppDB.AVATAR_URL)
    val avatarUrl: String? = null,

/*    @SerializedName("type")
    val type:UserType*/
)
//@Keep
enum class UserType{
    // Необходимо помечать все поля
    @SerializedName("User",alternate = ["USER"])
    USER,
    @SerializedName("Admin",alternate = ["ADMIN"])
    ADMIN
}