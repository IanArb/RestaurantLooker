package com.ianarbuckle.booking.ui.reservation.core.interactor

import android.Manifest
import android.app.Activity
import androidx.annotation.RequiresPermission
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.data.Booking
import com.ianarbuckle.booking.data.Owner
import com.ianarbuckle.booking.data.Table
import com.ianarbuckle.booking.data.TableCharacteristics
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.core.utils.FormFieldValidator.notBlank
import com.ianarbuckle.core.utils.FormFieldValidator.validEmailAddress
import com.ianarbuckle.models.Country

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationInteractor {
    fun getRestaurantsName(): String
    fun isFullnameValid(fullName: String): Boolean
    fun isEmailValid(email: String): Boolean
    fun isPhoneNumberValid(phoneNumber: String): Boolean
    fun createBookingRequest(properties: Map<String, String>): Booking
    fun isBookingDateValid(bookingDate: String): Boolean
    fun isArrivalTimeValid(arrivalTime: String): Boolean
    suspend fun saveBooking(booking: Booking)
    fun getPhonePrefix(): String?
}

class ReservationInteractorImpl(private val activity: Activity, private val repository: ReservationRepository, private val countryName: String) : ReservationInteractor {

    override fun getRestaurantsName(): String = activity.intent.getStringExtra("NAME")

    override fun isFullnameValid(fullName: String): Boolean = notBlank(fullName).isSuccess

    override fun isEmailValid(email: String): Boolean = validEmailAddress(email).isSuccess

    override fun isPhoneNumberValid(phoneNumber: String): Boolean = notBlank(phoneNumber).isSuccess

    override fun isBookingDateValid(bookingDate: String): Boolean = notBlank(bookingDate).isSuccess

    override fun isArrivalTimeValid(arrivalTime: String): Boolean = notBlank(arrivalTime).isSuccess

    @RequiresPermission(value = Manifest.permission.READ_PHONE_STATE)
    override fun createBookingRequest(properties: Map<String, String>): Booking {
        val owner: Owner
        val uuid = DeviceUuidFactory(activity).getDeviceUuid().toString()
        properties.let {
            val email = it["email"]
            val phoneNumber = it["phoneNumber"]
            val fullname = it["fullname"]
            val dietaryRequirements = it["dietaryRequirements"]
            val arrivalTime = it["arrivalTime"]
            val bookingDate = it["bookingDate"]
            owner = Owner(uuid, fullname, email, phoneNumber?.toInt(), dietaryRequirements?.toBoolean(), bookingDate, arrivalTime)
        }

        return Booking(owner, getRestaurantsName(), Table("1", "RESERVED", TableCharacteristics("COUPLE", 2, false)))
    }

    override suspend fun saveBooking(booking: Booking) {
        repository.saveBooking(booking)
    }

    override fun getPhonePrefix(): String? {
        val json = activity.resources.openRawResource(R.raw.countries).bufferedReader().use { it.readText() }

        val listType = object : TypeToken<List<Country>>() {}.type
        val countries = GsonBuilder().create().fromJson<List<Country>>(json, listType)

        return countries.find { it.iso == countryName }?.phoneCode
    }

}