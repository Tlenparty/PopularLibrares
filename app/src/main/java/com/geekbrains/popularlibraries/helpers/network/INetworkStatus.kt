package com.geekbrains.popularlibraries.helpers.network

import io.reactivex.Observable
import io.reactivex.Single

interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}