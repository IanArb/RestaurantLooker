package utils

import com.ianarbuckle.restaurants.home.model.*

/**
 * Created by Ian Arbuckle on 19/08/2018.
 *
 */
fun buildRestaurantMock() : MutableList<Restaurant> {
    val restaurants = mutableListOf<Restaurant>()

    val restaurantData = Restaurant("1", getDataList())

    restaurants.add(restaurantData)

    return restaurants
}

private fun getDataList(): MutableList<Restaurants> {
    val restaurants = mutableListOf<Restaurants>()

    restaurants.apply {
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()),"https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
        add(Restaurants("Cirillo's", "Description", "Dublin", "Dublin", "Dublin",
                Location(0.5f, 0.10f), "OPEN", Menu(createLunchMenu(), createDinnerMenu()), "https://media-cdn.tripadvisor.com/media/photo-s/03/c8/00/12/paulie-s-pizza.jpg"))
    }

    return restaurants
}

private fun createLunchMenu(): MutableList<Dish> {
    val lunch = mutableListOf<Dish>()

    lunch.apply {
        add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
        add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
        add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))
    }

    return lunch
}

private fun createDinnerMenu(): MutableList<Dish> {
    val dinner = mutableListOf<Dish>()

    dinner.apply {
        add(Dish("STARTER", "Soup of the day", "Fresh soup of the day", Price("EUR", 5.5f)))
        add(Dish("PIZZA", "MARGHERITA", "Tomato Sauce, Mozzarella, Parmesan & Fresh Basil", Price("EUR", 9f)))
        add(Dish("DESERT", "Cheese cake", "Strawberry cheese cake", Price("EUR", 6.5f)))
    }

    return dinner
}