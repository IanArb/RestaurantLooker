package utils

import com.ianarbuckle.restaurants.data.*

/**
 * Created by Ian Arbuckle on 19/08/2018.
 *
 */
fun buildRestaurantMock() : MutableList<Restaurant> {
    val restaurants = mutableListOf<Restaurant>()

    val restaurantData = createRestaurant()

    restaurants.add(restaurantData)

    return restaurants
}

fun createRestaurant(): Restaurant {
    val menu = createMenu()
    return Restaurant("1", "Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
            Location(0.5f, 0.10f), "OPEN", menu, "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg")
}

fun createMenu() = Menu(createLunchMenu())

private fun createLunchMenu(): MutableList<Dish> {
    val lunch = mutableListOf<Dish>()

    lunch.apply {
        add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
        add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
        add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))
    }

    return lunch
}