package com.example.taskmanager

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.example.taskmanager.data.local.AppDatabase
import com.google.firebase.firestore.FirebaseFirestore

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        firebaseDB = FirebaseFirestore.getInstance()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var firebaseDB: FirebaseFirestore?=null
        lateinit var db: AppDatabase
    }
}