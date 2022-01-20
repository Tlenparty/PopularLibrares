package com.geekbrains.popularlibraries.model.db.dao

import androidx.room.*
import com.geekbrains.popularlibraries.model.db.AppDB
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GithubUserRepositoryDao {

    //вставить репозитории
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<GithubUserRepository>): Completable

    //получить пользователей
    @Query(
        "select r.* " +
                "from ${AppDB.TABLE_USER_REPOSITORIES} r, " +
                "${AppDB.TABLE_USERS} u " +
                "where r.${AppDB.USER_ID} = u.${AppDB.USER_ID} " +
                "and u.${AppDB.LOGIN} = :userLogin"
    )
    fun getRepositories(userLogin: String): Single<List<GithubUserRepository>>

    //получить пользователя
    @Query(
        "select r.* " +
                "from ${AppDB.TABLE_USER_REPOSITORIES} r, " +
                "${AppDB.TABLE_USERS} u " +
                "where r.${AppDB.NAME} = :repositoryName " +
                "and r.${AppDB.USER_ID} = u.${AppDB.USER_ID} " +
                "and u.${AppDB.LOGIN} = :userLogin"
    )
    fun getRepositoryOnUserLogin(
        userLogin: String,
        repositoryName: String
    ): Single<GithubUserRepository>

}