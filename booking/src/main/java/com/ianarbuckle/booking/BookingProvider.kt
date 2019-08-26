package com.ianarbuckle.booking

import com.ianarbuckle.booking.builder.BookingInjector

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
object BookingProvider {

    private lateinit var injector: BookingInjector

    fun init(injector: BookingInjector): BookingProvider {
        this.injector = injector
        return this
    }

    fun get(): BookingInjector = injector
}