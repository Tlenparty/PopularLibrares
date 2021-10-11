package com.geekbrains.popularlibraries.helpers.scheduler

import io.reactivex.Scheduler

interface Schedulers {
    fun background(): Scheduler
    fun main():Scheduler
}