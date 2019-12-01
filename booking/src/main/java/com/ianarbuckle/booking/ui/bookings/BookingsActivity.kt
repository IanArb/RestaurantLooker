package com.ianarbuckle.booking.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenter
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
class BookingsActivity: AppCompatActivity() {

    @Inject
    lateinit var view: BookingsView

    @Inject
    lateinit var presenter: BookingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BookingProvider.get().inject(this)
        setContentView(view.getView())
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}