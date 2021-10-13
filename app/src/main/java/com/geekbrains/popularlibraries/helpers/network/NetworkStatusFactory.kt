package com.geekbrains.popularlibraries.helpers.network

import android.content.Context

object NetworkStatusFactory {

    fun create(context: Context): INetworkStatus = NetworkStatusImpl(context)
}