package com.ianarbuckle.booking

import com.ianarbuckle.booking.builder.BookingInjector
import com.ianarbuckle.booking.builder.BookingInjectorImpl
import com.ianarbuckle.booking.ui.confirmation.ConfirmationActivity
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.database.client.DatabaseClient
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
data class BookingInitializer(private var baseUrl: String, private var okHttpClient: OkHttpClient, private var converterFactory: Converter.Factory,
                              private var navigator: BookingNavigator, private var country: String, private var databaseClient: DatabaseClient,
                              private var bookingsCallback: ((ConfirmationActivity) -> Unit)? = null, private var uuidFactory: DeviceUuidFactory) {

    fun init() {
        val injector: BookingInjector =
                BookingInjectorImpl(
                        baseUrl,
                        okHttpClient,
                        converterFactory,
                        navigator,
                        country,
                        bookingsCallback,
                        uuidFactory)
        BookingProvider.init(injector)
        injector.inject()
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var converterFactory: Converter.Factory
        private lateinit var navigator: BookingNavigator
        private lateinit var country: String
        private lateinit var databaseClient: DatabaseClient
        private var bookingsCallback: ((ConfirmationActivity) -> Unit)? = null
        private lateinit var uuidFactory: DeviceUuidFactory

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }
        fun withConverterFactory(converterFactory: Converter.Factory) = apply { this.converterFactory = converterFactory }
        fun withNavigator(navigator: BookingNavigator) = apply { this.navigator = navigator }
        fun withCountry(country: String) = apply { this.country = country }
        fun withDatabaseClient(databaseClient: DatabaseClient) = apply { this.databaseClient = databaseClient }
        fun withCallback(bookingsCallback: (ConfirmationActivity) -> Unit) = apply { this.bookingsCallback = bookingsCallback }
        fun withDeviceUuidFactory(uuidFactory: DeviceUuidFactory) = apply { this.uuidFactory = uuidFactory }

        fun build() : BookingInitializer {
            val bookingInitializer = BookingInitializer(
                    baseUrl,
                    okHttpClient,
                    converterFactory,
                    navigator,
                    country,
                    databaseClient,
                    bookingsCallback,
                    uuidFactory)
            bookingInitializer.init()
            return bookingInitializer
        }
    }
}

fun bookingInitializer(initializer: BookingInitializer.Builder.() -> Unit): BookingInitializer {
    return BookingInitializer.Builder().apply(initializer).build()
}