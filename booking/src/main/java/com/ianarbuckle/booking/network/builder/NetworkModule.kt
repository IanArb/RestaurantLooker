package com.ianarbuckle.booking.network.builder

import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.booking.network.manager.BookingServiceManagerImpl
import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.booking.network.repository.BookingsRepositoryImpl
import com.ianarbuckle.core.utils.DeviceUuidFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkScope

@Module
class NetworkModule(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory, private val uuidFactory: DeviceUuidFactory) {

    @NetworkScope
    @Provides
    fun provideBookingServiceManager(): BookingServiceManager = BookingServiceManagerImpl(okHttpClient, baseUrl, converterFactory)

    @NetworkScope
    @Provides
    fun provideRepository(serviceManager: BookingServiceManager): BookingsRepository = BookingsRepositoryImpl(serviceManager, uuidFactory)
}

@NetworkScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun repository(): BookingsRepository
}