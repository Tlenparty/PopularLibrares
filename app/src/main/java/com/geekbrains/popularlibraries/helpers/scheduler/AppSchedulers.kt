package com.geekbrains.popularlibraries.helpers.scheduler

import io.reactivex.Scheduler

interface AppSchedulers {
    fun background(): Scheduler
    fun main(): Scheduler
}