package com.ianarbuckle.restaurants.ui.home.model

/**
 * Created by Ian Arbuckle on 30/07/2018.
 *
 */

sealed class Result<out T: Any>

data class OnSuccess<out T: Any>(val data: T) : Result<T>()

data class OnFailure(val error: Throwable?) : Result<Nothing>()
