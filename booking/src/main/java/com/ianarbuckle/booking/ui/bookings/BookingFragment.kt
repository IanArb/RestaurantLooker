package com.ianarbuckle.booking.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingPresenter
import com.ianarbuckle.booking.ui.bookings.core.view.BookingView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
class BookingFragment: Fragment() {

    @Inject
    lateinit var view: BookingView

    @Inject
    lateinit var presenter: BookingPresenter

    companion object {
        val TAG: String = BookingFragment::class.java.simpleName
        fun newInstance() = BookingFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BookingProvider.get().inject(this)
        presenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.getView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}