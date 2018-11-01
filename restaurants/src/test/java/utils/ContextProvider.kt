package utils

import com.ianarbuckle.restaurants.utils.RestaurantsBuddyDispatchers
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Dispatchers
import kotlin.coroutines.experimental.CoroutineContext



/**
 * Created by Ian Arbuckle on 27/10/2018.
 *
 */
interface ContextProvider {
    fun UI(): CoroutineContext
}