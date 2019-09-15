package com.ianarbuckle.booking.builder

import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.ui.bookings.BookingsFragment
import com.ianarbuckle.booking.ui.bookings.builder.BookingModule
import com.ianarbuckle.booking.ui.bookings.builder.DaggerBookingComponent
import com.ianarbuckle.booking.ui.calendar.CalendarActivity
import com.ianarbuckle.booking.ui.calendar.builder.CalendarModule
import com.ianarbuckle.booking.ui.calendar.builder.DaggerCalendarComponent
import com.ianarbuckle.booking.ui.confirmation.ConfirmationActivity
import com.ianarbuckle.booking.ui.confirmation.builder.ConfirmationModule
import com.ianarbuckle.booking.ui.confirmation.builder.DaggerConfirmationComponent
import com.ianarbuckle.booking.ui.phonePrefix.PhonePrefixActivity
import com.ianarbuckle.booking.ui.phonePrefix.builder.DaggerPhonePrefixComponent
import com.ianarbuckle.booking.ui.phonePrefix.builder.PhonePrefixModule
import com.ianarbuckle.booking.ui.reservation.ReservationActivity
import com.ianarbuckle.booking.ui.reservation.builder.DaggerReservationComponent
import com.ianarbuckle.booking.ui.reservation.builder.ReservationModule
import com.ianarbuckle.core.utils.DeviceUuidFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingInjector {
    fun inject(fragment: BookingsFragment)
    fun inject(activity: ReservationActivity)
    fun inject(activity: CalendarActivity)
    fun inject(activity: PhonePrefixActivity)
    fun inject(activity: ConfirmationActivity)
}

class BookingInjectorImpl(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val converterFactory: Converter.Factory,
                          private val navigator: BookingNavigator, private val country: String, private val bookingsCallback: (ConfirmationActivity) -> Unit,
                          private val uuidFactory: DeviceUuidFactory): BookingInjector {

    override fun inject(fragment: BookingsFragment) {
        DaggerBookingComponent.builder()
                .bookingModule(BookingModule(fragment, uuidFactory))
                .networkModule(NetworkModule(okHttpClient, baseUrl, converterFactory))
                .build()
                .inject(fragment)
    }

    override fun inject(activity: ReservationActivity) {
        DaggerReservationComponent.builder()
                .reservationModule(ReservationModule(activity, navigator, country, uuidFactory))
                .networkModule(NetworkModule(okHttpClient, baseUrl, converterFactory))
                .build()
                .inject(activity)
    }

    override fun inject(activity: CalendarActivity) {
        DaggerCalendarComponent.builder()
                .calendarModule(CalendarModule(activity))
                .build()
                .inject(activity)
    }

    override fun inject(activity: PhonePrefixActivity) {
        DaggerPhonePrefixComponent.builder()
                .phonePrefixModule(PhonePrefixModule(activity))
                .build()
                .inject(activity)
    }

    override fun inject(activity: ConfirmationActivity) {
        DaggerConfirmationComponent.builder()
                .confirmationModule(ConfirmationModule(activity, bookingsCallback))
                .build()
                .inject(activity)
    }
}
