package com.geekbrains.popularlibraries.helpers

import android.content.Context
import java.io.File

//вспомогательный класс для работы с папками приложения
class FolderHelper(private val context: Context) {

    companion object {
        private const val IMAGE_FOLDER = "image"
    }

    private val externalFilesDir: File? = context.getExternalFilesDir(null)

    init {
        //создадим папку
        imageFolder()
            .takeIf { it.mkdirs() }
            ?.takeIf { it.exists() }
    }

    //получить папку с изображениями
    fun imageFolder() = File(externalFilesDir, IMAGE_FOLDER)
}