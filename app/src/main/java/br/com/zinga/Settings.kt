package br.com.zinga

import android.content.Context

object Settings {

    fun saveRegistration(context: Context, registration: String) {
        val preferences = context.getSharedPreferences("registration", Context.MODE_PRIVATE)
        preferences.edit().putString("registration", registration).apply()
    }

    fun getRegistration(context: Context) : String {
        val preferences = context.getSharedPreferences("registration", Context.MODE_PRIVATE)
        return preferences.getString("registration", "")
    }
}