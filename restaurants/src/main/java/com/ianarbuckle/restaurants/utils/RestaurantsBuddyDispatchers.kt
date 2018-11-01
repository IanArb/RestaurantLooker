package com.ianarbuckle.restaurants.utils

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.android.Main

/**
 * Created by Ian Arbuckle on 23/10/2018.
 *
 */
open class RestaurantsBuddyDispatchers {
    open val ui: CoroutineDispatcher = Dispatchers.Main
    open val io: CoroutineDispatcher = Dispatchers.IO
}