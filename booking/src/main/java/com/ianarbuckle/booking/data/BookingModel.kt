package com.ianarbuckle.booking.data

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */

data class Booking(val owner: Owner?, val restaurantName: String?, val table: Table?)

data class Owner(val uuid: String?, val name: String?, val email: String?, val phoneNumber: Int?, val dietaryRequirements: Boolean?, val bookingDate: String?, val arrivalTime: String?)

data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)
