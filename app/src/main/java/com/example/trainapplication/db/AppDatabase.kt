package com.example.trainapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trainapplication.SortTypeModel


@Database(
    entities = [SortTypeModel::class], version = 1, exportSchema = false
)

abstract class AppDatabase() : RoomDatabase() {

    abstract fun sortTypeDao(): SortTypeDao
}