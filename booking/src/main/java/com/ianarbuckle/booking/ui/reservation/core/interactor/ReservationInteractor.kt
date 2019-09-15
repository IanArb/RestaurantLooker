package com.ianarbuckle.booking.ui.reservation.core.interactor

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.annotation.RequiresPermission
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.ui.reservation.constants.Constants.ARRIVAL_TIME_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.BOOKING_DATE_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.CALENDAR_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.DIET_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.EMAIL_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.NAME_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.PHONE_CODE_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.PHONE_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.REQUEST_CODE_CALENDAR
import com.ianarbuckle.booking.ui.reservation.constants.Constants.REQUEST_CODE_PREFIX
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.core.utils.FormFieldValidator.notBlank
import com.ianarbuckle.core.utils.FormFieldValidator.validEmailAddress
import com.ianarbuckle.models.booking.*

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationInteractor {
    fun getRestaurantsName(): String?
    fun isFullnameValid(fullName: String): Boolean
    fun isEmailValid(email: String): Boolean
    fun isPhoneNumberValid(phoneNumber: String): Boolean
    fun createBookingRequest(properties: Map<String, String>): Booking
    fun isBookingDateValid(bookingDate: String): Boolean
    fun isArrivalTimeValid(arrivalTime: String): Boolean
    suspend fun saveBooking(booking: Booking)
    fun getPhonePrefix(): String?
    fun getDataFromActivityResult(requestCode: Int, resultCode: Int, data: Intent?): String?
}

class ReservationInteractorImpl(private val activity: Activity, private val repository: ReservationRepository, private val countryName: String,
                                private val uuidFactory: DeviceUuidFactory) : ReservationInteractor {

    override fun getRestaurantsName(): String? = activity.intent.getStringExtra("NAME")

    override fun isFullnameValid(fullName: String): Boolean = notBlank(fullName).isSuccess

    override fun isEmailValid(email: String): Boolean = validEmailAddress(email).isSuccess

    override fun isPhoneNumberValid(phoneNumber: String): Boolean = notBlank(phoneNumber).isSuccess

    override fun isBookingDateValid(bookingDate: String): Boolean = notBlank(bookingDate).isSuccess

    override fun isArrivalTimeValid(arrivalTime: String): Boolean = notBlank(arrivalTime).isSuccess

    @RequiresPermission(value = Manifest.permission.READ_PHONE_STATE)
    override fun createBookingRequest(properties: Map<String, String>): Booking {
        val owner: Owner
        val uuid = uuidFactory.getUUID()
        properties.let {
            val email = it[EMAIL_KEY]
            val number = it[PHONE_KEY]
            val code = it[PHONE_CODE_KEY]
            val fullname = it[NAME_KEY]
            val dietaryRequirements = it[DIET_KEY]
            val arrivalTime = it[ARRIVAL_TIME_KEY]
            val bookingDate = it[BOOKING_DATE_KEY]
            val phoneNumber = PhoneNumber(code!!.toInt(), number!!.toInt())
            owner = Owner(uuid, fullname, email, phoneNumber, dietaryRequirements?.toBoolean(), bookingDate, arrivalTime)
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

    override fun getDataFromActivityResult(requestCode: Int, resultCode: Int, data: Intent?): String? {
        var value: String? = null
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                REQUEST_CODE_CALENDAR -> value = data?.getStringExtra(CALENDAR_KEY)
                REQUEST_CODE_PREFIX -> value = data?.getStringExtra(PHONE_CODE_KEY)
            }
        }
        return value
    }
}