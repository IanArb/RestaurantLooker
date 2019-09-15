package com.ianarbuckle.booking.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.calendar.core.presenter.CalendarPresenter
import com.ianarbuckle.booking.ui.calendar.core.view.CalendarView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-09-02.
 *
 */
class CalendarActivity : AppCompatActivity() {

    @Inject
    lateinit var view: CalendarView

    @Inject
    lateinit var presenter: CalendarPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BookingProvider.get().inject(this)
        setContentView(view.getView())
        presenter.onCreate()
    }

}