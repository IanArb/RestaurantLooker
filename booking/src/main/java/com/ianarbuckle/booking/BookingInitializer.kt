package com.ianarbuckle.booking

import com.ianarbuckle.booking.builder.BookingInjector
import com.ianarbuckle.booking.builder.BookingInjectorImpl
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
data class BookingInitializer(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val converterFactory: Converter.Factory) {

    fun init() {
        val injector: BookingInjector = BookingInjectorImpl(baseUrl, okHttpClient, converterFactory)
        BookingProvider.init(injector)
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var converterFactory: Converter.Factory

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }
        fun withConverterFactory(converterFactory: Converter.Factory) = apply { this.converterFactory = converterFactory }

        fun build() {
            val initializer = BookingInitializer(baseUrl, okHttpClient, converterFactory)
            initializer.init()
        }
    }
}