package com.ianarbuckle.restaurants.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */

data class Restaurant(val id: String, val results: MutableList<Restaurants>)

@Parcelize
data class Restaurants(val restaurantName: String, val description: String, val county: String, val street: String,
                       val address: String, val location: @RawValue Location, val status: String, val menu: @RawValue Menu, val imageUrl: String)
    : Parcelable

data class Location(val latitude: Float, val longitude: Float)

data class Menu(val lunch: MutableList<Dish>, val dinner: MutableList<Dish>)

data class Dish(val courseType: String, val dishName: String, val description: String, val price: Price)

data class Price(val currency: String, val amount: Float)
