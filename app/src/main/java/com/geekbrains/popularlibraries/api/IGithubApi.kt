package com.geekbrains.popularlibraries.api

import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.entites.GithubUserRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
//@Url() аннатоция в которую можно закидывать url привызове запроса
/*@Path
Запрос может иметь изменяемые части пути. Посмотрите на один из примеров запроса для GitHub: /users/:username. Вместо :username следует подставлять конкретные имена пользователей (https://api.github.com/users/alexanderklimov). В таких случаях используют фигурные скобки в запросе, в самоме методе через аннотацию @Path указывается имя, которое будет подставляться в путь.*/
interface IGithubApi {
    //получить список пользователей
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    //получить конкретного пользователя
    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

    //получить список репозиториев пользователя
    @GET("/users/{login}/repos")
    fun getRepositories(@Path("login") login: String): Single<List<GithubUserRepository>>

    //получить список репозиториев пользователя
    @GET("/repos/{login}/{repository}")
    fun getRepository(
        @Path("login") login: String,
        @Path("repository") repository: String,
    ): Single<GithubUserRepository>
}
