package com.geekbrains.popularlibraries.framework.ui.adapters


// Интерфейс для Presenter с логикой (слушатель клика, получение кол-ва элементов, фу-я наполнения View
interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

// Интерфейс для адаптера
interface IUserListPresenter : IListPresenter<UserItemView>