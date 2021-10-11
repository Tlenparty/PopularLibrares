package com.geekbrains.popularlibraries.framework.ui.images

interface IImageLoader<T> {

    // Будет загружать некий target
    fun load(url: String, target: T)
}