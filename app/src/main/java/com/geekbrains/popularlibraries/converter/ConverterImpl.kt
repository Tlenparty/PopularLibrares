package com.geekbrains.popularlibraries.converter

import android.content.Context
import android.net.Uri
import io.reactivex.Single
import javax.inject.Inject

class ConverterImpl @Inject constructor(private val context: Context) : Converter {

    override fun convert(uri: Uri): Single<Uri> = ConverterSingle(context, uri)

}