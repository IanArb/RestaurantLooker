package com.ianarbuckle.booking.ui.bookings.core.presenter

import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingInteractor
import com.ianarbuckle.booking.ui.bookings.core.router.BookingRouter
import com.ianarbuckle.booking.ui.bookings.core.view.BookingView

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingPresenter {
    fun onCreate()
    fun onDestroy()
}

class BookingPresenterImpl(private val view: BookingView, private val interactor: BookingInteractor,
                           private val router: BookingRouter) : BookingPresenter {

    override fun onCreate() {

    }

    override fun onDestroy() {
    }
}