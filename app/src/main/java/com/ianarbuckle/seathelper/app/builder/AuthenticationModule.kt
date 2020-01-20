package com.ianarbuckle.seathelper.app.builder

import com.ianarbuckle.authenticationInitializer
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.core.utils.DeviceUuidFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AuthenticationModule() {

    @AppScope
    @Provides
    fun provideAuthenticationClient(@Named("baseUrl") baseUrl: String,
                                    okHttpClient: OkHttpClient,
                                    uuidFactory: DeviceUuidFactory): AuthenticationClient = authenticationInitializer {
        withBaseUrl(baseUrl)
        withConverters(GsonConverterFactory.create())
        withOkHttpClient(okHttpClient)
        withUuidFactory(uuidFactory)
    }
}