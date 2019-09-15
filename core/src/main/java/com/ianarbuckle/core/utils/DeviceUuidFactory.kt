package com.ianarbuckle.core.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.UUID

class DeviceUuidFactory(private val context: Context) {
    private var uniqueID: String? = null

    companion object {
        private const val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"
    }

    @Synchronized
    fun getUUID(): String? {
        if (uniqueID == null) {
            val sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE)
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null)
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString()
                val editor = sharedPrefs.edit()
                editor.putString(PREF_UNIQUE_ID, uniqueID)
                editor.apply()
            }
        }
        return uniqueID
    }

}