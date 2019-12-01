package com.ianarbuckle.booking.builder

import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.network.builder.DaggerNetworkComponent
import com.ianarbuckle.booking.network.builder.NetworkComponent
import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.booking.ui.booking.BookingFragment
import com.ianarbuckle.booking.ui.booking.builder.BookingModule
import com.ianarbuckle.booking.ui.booking.builder.DaggerBookingComponent
import com.ianarbuckle.booking.ui.bookings.BookingsActivity
import com.ianarbuckle.booking.ui.bookings.builder.BookingsModule
import com.ianarbuckle.booking.ui.bookings.builder.DaggerBookingsComponent
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
    fun inject(fragment: BookingFragment)
    fun inject(activity: ReservationActivity)
    fun inject(activity: CalendarActivity)
    fun inject(activity: PhonePrefixActivity)
    fun inject(activity: ConfirmationActivity)
    fun inject(activity: BookingsActivity)
    fun inject(): BookingsRepository
}

class BookingInjectorImpl(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val converterFactory: Converter.Factory,
                          private val navigator: BookingNavigator, private val country: String, private val bookingsCallback: ((ConfirmationActivity) -> Unit)? = null,
                          private val uuidFactory: DeviceUuidFactory): BookingInjector {

    private lateinit var networkComponent: NetworkComponent

    override fun inject(fragment: BookingFragment) {
        DaggerBookingComponent.builder()
                .bookingModule(BookingModule(fragment))
                .networkComponent(networkComponent)
                .build()
                .inject(fragment)
    }

    override fun inject(activity: ReservationActivity) {
        DaggerReservationComponent.builder()
                .reservationModule(ReservationModule(activity, navigator, country, uuidFactory))
                .networkComponent(networkComponent)
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

    override fun inject(activity: BookingsActivity) {
        DaggerBookingsComponent.builder()
                .bookingsModule(BookingsModule(activity, uuidFactory))
                .networkComponent(networkComponent)
                .build()
                .inject(activity)
    }

    override fun inject(): BookingsRepository {
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule(okHttpClient, baseUrl, converterFactory, uuidFactory))
                .build()
        return networkComponent.repository()
    }
}
