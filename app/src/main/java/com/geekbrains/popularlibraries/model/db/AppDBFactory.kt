package com.geekbrains.popularlibraries.model.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object AppDBFactory {
    // Миграция бд
   /* val MIGRATION_1_2 = object : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE RoomGithubUser ADD COLUMN url TEXT DEFAULT '' NOT NULL" )
        }
    }
*/
    fun create(context: Context): AppDB =
        // Созданием бд
        Room.databaseBuilder(context, AppDB::class.java, AppDB.DB_NAME)
            //.addMigrations() Миграция бд
            .build()
}
/*
Или так можно
private var instance: AppDB? = null
fun getInstance() = instance ?: IllegalStateException("база данных не инициализирована")

fun create(context: Context) {
    if (instance == null) {
        // Созданием бд
        instance = Room.databaseBuilder (context, AppDB::class.java, AppDB.DB_NAME)
            .build()
    }
}
}*/
