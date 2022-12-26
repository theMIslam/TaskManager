package com.example.taskmanager

import android.app.Application
import androidx.room.Room
import com.example.taskmanager.data.local.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}