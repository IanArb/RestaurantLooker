package com.ianarbuckle.models.booking

import android.os.Parcelable
import com.ianarbuckle.models.restaurant.Location
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
@Parcelize
data class Booking(val owner: Owner?, val restaurantDetails: RestaurantDetails?, val table: Table?): Parcelable

@Parcelize
data class RestaurantDetails(val name: String?, val imageUrl: String?, val address: String?, val location: Location?) : Parcelable

@Parcelize
data class Owner(val uuid: String?, val name: String?, val email: String?, val phoneNumber: PhoneNumber?, val dietaryRequirements: Boolean?, val bookingDate: String?, val arrivalTime: String?): Parcelable

@Parcelize
data class PhoneNumber(val code: Int, val number: Int): Parcelable

@Parcelize
data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics): Parcelable

@Parcelize
data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean): Parcelable
