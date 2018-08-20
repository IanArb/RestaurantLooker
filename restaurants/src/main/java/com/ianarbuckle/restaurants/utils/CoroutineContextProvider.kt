package com.ianarbuckle.restaurants.utils

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Ian Arbuckle on 19/08/2018.
 *
 */
open class CoroutineContextProvider() {
    open val Main: CoroutineContext by lazy { UI }
    open val IO: CoroutineContext by lazy { CommonPool }
}