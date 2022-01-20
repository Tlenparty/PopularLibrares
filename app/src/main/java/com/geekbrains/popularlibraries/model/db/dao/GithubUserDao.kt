package com.geekbrains.popularlibraries.model.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.geekbrains.popularlibraries.model.db.AppDB
import com.geekbrains.popularlibraries.model.entites.GithubUser
import io.reactivex.Completable
import io.reactivex.Single

//Обьект доступа к данным
//@Delete - позволяет удалить данные
@Dao
interface GithubUserDao {

    //вставить пользователей
    //onConflict если добавляем пользователя, который есть уже в бд с таким primary key то REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GithubUser>): Completable

    //получить пользователей из таблцы
    @Query("select * from ${AppDB.TABLE_USERS}")
    fun getUsers(): Single<List<GithubUser>>

    //получить пользователя из таблицы ...userLogin LIMIT1" можно указать еще лимит
    @Query("select * from ${AppDB.TABLE_USERS} where ${AppDB.LOGIN} = :userLogin")
    fun getUser(userLogin: String): Single<GithubUser>


    /*   //получить всех пользователей по из бд по ее имени
       @Query("select * from ${AppDB.DB_NAME} ")
       fun getAllUsers(): List<GithubUser>*/

    //обновить данные
    @Update(onConflict = REPLACE)
    fun retain(user: GithubUser): Completable
}