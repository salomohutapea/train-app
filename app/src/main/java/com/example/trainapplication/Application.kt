package com.example.trainapplication

import android.app.Application
import androidx.room.Room
import com.example.trainapplication.db.AppDatabase

class Application : Application() {

    companion object {
        var db: AppDatabase? = null
        fun getDatabase(): AppDatabase? {
            return db
        }
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "train-db")
            .allowMainThreadQueries().build()
    }
}