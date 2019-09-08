package com.ianarbuckle.booking.ui.calendar.core.presenter

import com.ianarbuckle.booking.ui.calendar.core.router.CalendarRouter
import com.ianarbuckle.booking.ui.calendar.core.view.CalendarView

/**
 * Created by Ian Arbuckle on 2019-09-02.
 *
 */
interface CalendarPresenter {
    fun onCreate()
}

class CalendarPresenterImpl(private val view: CalendarView, private val router: CalendarRouter) : CalendarPresenter {

    override fun onCreate() {
//        view.onCalendarClick {
//            router.navigateToReservationWithDate(it.toString())
//        }
    }
}