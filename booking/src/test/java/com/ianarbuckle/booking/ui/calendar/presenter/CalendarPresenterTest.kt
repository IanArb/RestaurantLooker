package com.ianarbuckle.booking.ui.calendar.presenter

import com.ianarbuckle.booking.ui.calendar.core.presenter.CalendarPresenter
import com.ianarbuckle.booking.ui.calendar.core.presenter.CalendarPresenterImpl
import com.ianarbuckle.booking.ui.calendar.core.router.CalendarRouter
import com.ianarbuckle.booking.ui.calendar.core.view.CalendarView
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-09.
 *
 */
class CalendarPresenterTest {

    @Mock
    lateinit var view: CalendarView

    @Mock
    lateinit var router: CalendarRouter

    lateinit var presenter: CalendarPresenter

    @Before
    fun setup() {
        initMocks(this)
        presenter = CalendarPresenterImpl(view, router)
    }

    @Test
    fun `verify that onCreate on toolbar click it should navigate back`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).toolbarClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateBack()
    }

    @Test
    fun `verify that onCreate on calendar date click it should navigate back with date`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<(String?) -> Unit>()
        verify(view).calendarDateClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke("12/12/2020")

        verify(router, times(1)).navigateToReservationWithDate(anyString())
    }
}