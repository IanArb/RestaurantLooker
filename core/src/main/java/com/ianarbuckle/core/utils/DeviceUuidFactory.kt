package com.ianarbuckle.core.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import java.util.*
import java.util.UUID.randomUUID
import java.util.UUID.nameUUIDFromBytes
import androidx.core.content.ContextCompat.getSystemService
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission
import java.io.UnsupportedEncodingException
import java.lang.RuntimeException
import java.nio.charset.Charset


/**
 * Created by Ian Arbuckle on 2019-09-01.
 *
 */
@SuppressLint("HardwareIds")
class DeviceUuidFactory(private val context: Context) {

    companion object {
        private const val PREFS_FILE = "device_id.xml"
        private const val PREFS_DEVICE_ID = "device_id"
    }

    private lateinit var uuid: UUID

    @RequiresPermission(value = Manifest.permission.READ_PHONE_STATE)
    fun getDeviceUuid(): UUID {
            synchronized(DeviceUuidFactory::class) {
                val preferences = context.getSharedPreferences(PREFS_FILE, 0)
                val id = preferences.getString(PREFS_DEVICE_ID, null)

                if(id == null) {
                    uuid = UUID.fromString(id)
                    return uuid
                } else {
                    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

                    try {
                        return if ("9774d56d682e549c" != androidId) {
                            uuid = nameUUIDFromBytes(androidId.byteInputStream(Charset.forName("utf8")).readBytes())
                            uuid
                        } else {
                            val deviceId = (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
                            uuid = if (deviceId != null) nameUUIDFromBytes(deviceId.toByteArray(charset("utf8"))) else UUID.randomUUID()
                            uuid
                        }
                    } catch (e: UnsupportedEncodingException) {
                        throw RuntimeException(e)
                    }
                }
            }
    }
}