package com.ianarbuckle

import com.ianarbuckle.builder.AuthenticationInjector
import com.ianarbuckle.builder.AuthenticationInjectorImpl
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.core.utils.DeviceUuidFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

data class AuthenticationInitializer(private val baseUrl: String,
                                     private val okHttpClient: OkHttpClient,
                                     private val converters: Converter.Factory,
                                     private val uuidFactory: DeviceUuidFactory) {

    fun init(): AuthenticationClient {
        val injector: AuthenticationInjector = AuthenticationInjectorImpl(okHttpClient, baseUrl, converters, uuidFactory)
        return injector.inject()
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var converters: Converter.Factory
        private lateinit var uuidFactory: DeviceUuidFactory

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }
        fun withConverters(converters: Converter.Factory) = apply { this.converters = converters }
        fun withUuidFactory(uuidFactory: DeviceUuidFactory) = apply { this.uuidFactory = uuidFactory }

        fun build(): AuthenticationClient {
            val initializer = AuthenticationInitializer(baseUrl, okHttpClient, converters, uuidFactory)
            return initializer.init()
        }
    }
}

fun authenticationInitializer(initializer: AuthenticationInitializer.Builder.() -> Unit): AuthenticationClient {
    return AuthenticationInitializer.Builder().apply(initializer).build()
}