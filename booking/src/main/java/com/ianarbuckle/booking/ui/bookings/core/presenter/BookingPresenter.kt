package com.ianarbuckle.booking.ui.bookings.core.presenter

import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingInteractor
import com.ianarbuckle.booking.ui.bookings.router.BookingRouter
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}