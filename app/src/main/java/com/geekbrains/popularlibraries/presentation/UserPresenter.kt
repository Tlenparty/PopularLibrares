package com.geekbrains.popularlibraries.presentation

import com.geekbrains.popularlibraries.framework.ui.view.user_fragment.UserView
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val userLogin:String?
): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
       loadData()
    }

     fun loadData(){
         //получаем информацию о пользователе
         userLogin?.let {
             val user = usersRepo.getUser(it)

             //уведомляем view о том, что получили информацию о пользователе
             user.let { githubUser ->
                 viewState.showUser(githubUser.login)
             }
         }
     }

}
