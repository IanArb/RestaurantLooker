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
data class BookingInitializer(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val converterFactory: Converter.Factory,
                              private val navigator: BookingNavigator, private val country: String, private val databaseClient: DatabaseClient,
                              private val bookingsCallback: (ConfirmationActivity) -> Unit, private val uuidFactory: DeviceUuidFactory) {

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
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var converterFactory: Converter.Factory
        private lateinit var navigator: BookingNavigator
        private lateinit var country: String
        private lateinit var databaseClient: DatabaseClient
        private lateinit var bookingsCallback: (ConfirmationActivity) -> Unit
        private lateinit var uuidFactory: DeviceUuidFactory

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }
        fun withConverterFactory(converterFactory: Converter.Factory) = apply { this.converterFactory = converterFactory }
        fun withNavigator(navigator: BookingNavigator) = apply { this.navigator = navigator }
        fun withCountry(country: String) = apply { this.country = country }
        fun withDatabaseClient(databaseClient: DatabaseClient) = apply { this.databaseClient = databaseClient }
        fun withCallback(bookingsCallback: (ConfirmationActivity) -> Unit) = apply { this.bookingsCallback = bookingsCallback }
        fun withDeviceUuidFactory(uuidFactory: DeviceUuidFactory) = apply { this.uuidFactory = uuidFactory }

        fun build() {
            val initializer =
                    BookingInitializer(
                    baseUrl,
                    okHttpClient,
                    converterFactory,
                    navigator,
                    country,
                    databaseClient,
                    bookingsCallback,
                    uuidFactory)
            initializer.init()
        }
    }
}