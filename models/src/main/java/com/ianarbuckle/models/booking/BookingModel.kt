package com.ianarbuckle.models.booking

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
@Parcelize
data class Booking(val owner: @RawValue Owner?, val restaurantName: String?, val table: @RawValue Table?): Parcelable

@Parcelize
data class Owner(val uuid: String?, val name: String?, val email: String?, val phoneNumber: @RawValue PhoneNumber?, val dietaryRequirements: Boolean?, val bookingDate: String?, val arrivalTime: String?): Parcelable

@Parcelize
data class PhoneNumber(val code: Int, val number: Int): Parcelable

@Parcelize
data class Table(val tableNumber: String, val status: String, val characteristics: @RawValue TableCharacteristics): Parcelable

@Parcelize
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean): Parcelable
