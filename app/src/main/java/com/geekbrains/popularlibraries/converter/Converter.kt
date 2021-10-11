package com.geekbrains.popularlibraries.converter

import android.net.Uri
import io.reactivex.Single

interface Converter {
    fun convert(uri: Uri): Single<Uri>
}