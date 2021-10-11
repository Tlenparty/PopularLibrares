package com.geekbrains.popularlibraries.helpers.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AppSchedulersImpl:AppSchedulers {

    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

    override fun main(): Scheduler  = AndroidSchedulers.mainThread()
}