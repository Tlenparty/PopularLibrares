package com.geekbrains.popularlibraries.helpers.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

//проверка наличия интернета
class NetworkStatusImpl @Inject constructor(context: Context) : INetworkStatus {

    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private val networkCallback: ConnectivityManager.NetworkCallback
    private val connectivityManager: ConnectivityManager

    init {
        statusSubject.onNext(false) // По умолчанию будет отдавать что сети нет
        /*   // Запрос системного сервиса
           val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
           // Request будем отправлять в менеджер сети
           val request = NetworkRequest.Builder().build()
           connectivityManager.registerNetworkCallback(request, object:
               ConnectivityManager.NetworkCallback() {*/

        networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                statusSubject.onNext(true)
            }

            override fun onUnavailable() {
                statusSubject.onNext(false)
            }

            override fun onLost(network: Network) {
                statusSubject.onNext(false)
            }
        }
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    override fun isOnline() = statusSubject

    override fun isOnlineSingle() = statusSubject.first(false)
    override fun unBind() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}