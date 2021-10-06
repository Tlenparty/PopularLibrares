package com.geekbrains.popularlibraries.model.converter

import android.content.Context

object ConverterFactory {
    fun create(context: Context): Converter = ConverterImpl(context)
}