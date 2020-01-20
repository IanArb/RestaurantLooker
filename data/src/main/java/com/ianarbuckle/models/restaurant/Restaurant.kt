package com.ianarbuckle.models.restaurant

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
@Entity(tableName = "restaurant")
@Parcelize
data class Restaurant(@PrimaryKey val id: String, val restaurantName: String, val description: String, val county: String, val street: String, val address: String, val location: @RawValue Location, val status: String,
                      val dishes: MutableList<Dish>, val imageUrl: String) : Parcelable

@Parcelize
data class Location(val latitude: Float, val longitude: Float) : Parcelable

@Parcelize
data class Dish(val courseType: String, val dishName: String, val description: String, val price: @RawValue Price) : Parcelable

@Parcelize
data class Price(val currency: String, val amount: Float) : Parcelable