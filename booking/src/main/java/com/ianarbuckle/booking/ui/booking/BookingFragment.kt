package com.ianarbuckle.booking.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.booking.core.presenter.BookingPresenter
import com.ianarbuckle.booking.ui.booking.core.view.BookingView
import javax.inject.Inject

class BookingFragment : Fragment() {

    companion object {
        val TAG: String = BookingFragment::class.java.simpleName
        fun newInstance(): BookingFragment = BookingFragment()
    }

    @Inject
    lateinit var presenter: BookingPresenter

    @Inject
    lateinit var view: BookingView

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