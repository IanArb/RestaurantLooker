package com.ianarbuckle.restaurants.home.model

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */

data class Restaurant(val id: String, val results: MutableList<Restaurants>)

data class Restaurants(val restaurantName: String, val description: String, val county: String, val street: String,
                       val address: String, val location: Location, val status: String, val menu: Menu)

data class Location(val latitude: Float, val longitude: Float)

data class Menu(val lunch: MutableList<Dish>, val dinner: MutableList<Dish>)

data class Dish(val courseType: String, val dishName: String, val description: String, val price: Price)

data class Price(val currency: String, val amount: Float)
