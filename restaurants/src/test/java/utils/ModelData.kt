package utils

import com.ianarbuckle.models.restaurant.Dish
import com.ianarbuckle.models.restaurant.Location
import com.ianarbuckle.models.restaurant.Price
import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 19/08/2018.
 *
 */

object ModelData {

    fun buildRestaurantsModel(): MutableList<Restaurant> {
        val restaurants = mutableListOf<Restaurant>()

        val restaurantData = createRestaurant()

        restaurants.add(restaurantData)

        return restaurants
    }

    fun createRestaurant(): Restaurant {
        return Restaurant("1", "Buckle's",  "Cirillo's", "Dublin", "Dublin", "Dublin", Location(0.5f, 0.5f),
                "OPEN", createMenu(), "imageUrl")
    }

    private fun createMenu(): MutableList<Dish> {
        val lunch = mutableListOf<Dish>()

        lunch.add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
        lunch.add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
        lunch.add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))

        return lunch
    }

}
