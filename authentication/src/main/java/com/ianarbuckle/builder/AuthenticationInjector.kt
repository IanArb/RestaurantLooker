package com.ianarbuckle.builder

import com.ianarbuckle.authentication.builder.AuthenticationModule
import com.ianarbuckle.authentication.builder.DaggerAuthenticationComponent
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.core.utils.DeviceUuidFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

interface AuthenticationInjector {
    fun inject(): AuthenticationClient
}

class AuthenticationInjectorImpl(private val okHttpClient: OkHttpClient,
                                 private val baseUrl: String,
                                 private val converters: Converter.Factory,
                                 private val uuidFactory: DeviceUuidFactory) : AuthenticationInjector {

    override fun inject(): AuthenticationClient {
        return DaggerAuthenticationComponent.builder()
                .authenticationModule(AuthenticationModule(baseUrl, okHttpClient, converters, uuidFactory))
                .build()
                .client()
    }
}