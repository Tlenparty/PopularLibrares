package com.geekbrains.popularlibraries.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geekbrains.popularlibraries.model.db.dao.GithubUserDao
import com.geekbrains.popularlibraries.model.db.dao.GithubUserRepositoryDao
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository

@Database(
    entities = [GithubUser::class, GithubUserRepository::class],
    // Если меняем что-то в таблице меняем версию
    version = AppDB.DB_VERSION,
    exportSchema = true
)
abstract class AppDB : RoomDatabase() {

    abstract fun githubUserDao(): GithubUserDao
    abstract fun githubUserRepositoryDao(): GithubUserRepositoryDao
    // Синглтон
    companion object {
        //база данных
        const val DB_VERSION = 1
        const val DB_NAME = "app.db"

        //таблицы
        const val TABLE_USERS = "users"
        const val TABLE_USER_AVATARS = "user_avatar"
        const val TABLE_USER_REPOSITORIES = "user_repositories"

        //столбцы
        const val USER_ID = "user_id"
        const val LOGIN = "login"
        const val AVATAR_URL = "avatar_url"
        const val REPOSITORY_ID = "repository_id"
        const val NAME = "name"
        const val FULL_NAME = "full_name"
        const val FORKS = "forks"
        const val SIZE = "size"
        const val CREATED = "created"
    }

}