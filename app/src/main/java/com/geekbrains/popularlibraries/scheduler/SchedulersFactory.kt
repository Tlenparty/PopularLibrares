package com.geekbrains.popularlibraries.scheduler

object SchedulersFactory {
    fun create(): Schedulers = DefaultSchedulers()
}