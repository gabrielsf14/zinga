package br.com.zinga.utils

import android.content.Context
import android.preference.PreferenceManager

object Preferences {

    fun save(context: Context, key:String, value:String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun load(context: Context, key: String) : String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, "")
    }
}