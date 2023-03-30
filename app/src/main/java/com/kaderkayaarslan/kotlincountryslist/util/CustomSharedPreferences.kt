package com.kaderkayaarslan.kotlincountryslist.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object {
        private val PREFERENCES_TIME = "preferences"
        private var sharedPreferences: SharedPreferences? = null
        @Volatile private var instance: CustomSharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock ) {
            instance ?: makeCustomSharedPrefernces(context).also {
                instance = it
            }

        }

        private fun makeCustomSharedPrefernces(context: Context) : CustomSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }
    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }
    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)
}