package com.geekbrains.popularlibraries

class MainPresenter(private val view:MainView, private val model: CountersModel) {

    fun counterOneButtonClick(){
        val counter = model.next(0).toString()
        view.setOneButtonText(counter)
    }

    fun counterTwoButtonClick(){
        val counter = model.next(1).toString()
        view.setTwoButtonText(counter)
    }

    fun counterThreeButtonClick(){
        val counter = model.next(2).toString()
        view.setThreeButtonText(counter)
    }
}