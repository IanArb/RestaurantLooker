package com.ianarbuckle.restaurants.data


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */

@Parcelize
data class Restaurant(val id: String, val restaurantName: String, val description: String, val county: String, val street: String,
                      val address: String, val location: @RawValue Location, val status: String, val menu: @RawValue Menu, val imageUrl: String)
    : Parcelable

@Parcelize
data class Location(val latitude: Float, val longitude: Float) : Parcelable

@Parcelize
data class Menu(val dishes: @RawValue MutableList<Dish>) : Parcelable

@Parcelize
data class Dish(val courseType: String, val dishName: String, val description: String, val price: @RawValue Price) : Parcelable

@Parcelize
data class Price(val currency: String, val amount: Float) : Parcelable
