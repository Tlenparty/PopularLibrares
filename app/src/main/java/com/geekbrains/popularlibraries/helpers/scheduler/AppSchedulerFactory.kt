package com.geekbrains.popularlibraries.helpers.scheduler

object AppSchedulerFactory {
    fun create(): AppSchedulers = AppSchedulersImpl()
}