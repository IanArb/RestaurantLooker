package com.ianarbuckle.booking.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenter
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsView
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsViewImpl
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
class BookingsFragment: Fragment() {

    @Inject
    lateinit var view: BookingsView

    @Inject
    lateinit var presenter: BookingsPresenter

    companion object {
        val TAG: String = BookingsFragment::class.java.simpleName
        fun newInstance() = BookingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        BookingProvider.get().inject(this)
        return view.getView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}