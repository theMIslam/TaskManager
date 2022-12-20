package com.example.taskmanager.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref (private val  context: Context) {

    private  val pref=context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)


    fun  setOnBoardingSeen(isSeen: Boolean) {
        pref.edit().putBoolean("is_seen", isSeen).apply()
    }

    fun isOnBoardingSeen(): Boolean {
        return pref.getBoolean(ON_BOARDING_SEEN, false)
    }


    fun saveName(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String {
        return pref.getString(NAME_KEY, "").toString()
    }

    fun saveImage(image: String) {
        pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getImage(): String {
        return pref.getString(IMAGE_KEY, "").toString()
    }


    companion object {
        private const val  PREF_NAME = "pref_task_manager"
        private const val  NAME_KEY = "name.pref"
        private const val  IMAGE_KEY= "name.pref"
        private const val ON_BOARDING_SEEN = " is_seen"
    }
}