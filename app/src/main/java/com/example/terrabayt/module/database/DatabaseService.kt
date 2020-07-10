package com.example.module.data.database

import abbos.uzeu.database.CategoryDao
import abbos.uzeu.database.PostDao
import abbos.uzeu.database.PostSaveDao
import abbos2101.mvvmdemo.database.model.CategoryModelData
import abbos2101.mvvmdemo.database.model.PostModelData
import abbos2101.mvvmdemo.database.model.PostSaveModelData
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CategoryModelData::class,
        PostModelData::class,
        PostSaveModelData::class
    ],
    version = 2,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun postDao(): PostDao
    abstract fun postsaveDao(): PostSaveDao
}