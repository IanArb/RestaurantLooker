package com.ianarbuckle.seathelper.app.builder

import android.app.Application
import android.content.Context
import com.ianarbuckle.core.utils.DeviceUuidFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
@Module
class AppModule constructor(private val application: Application) {

    @AppScope
    @Provides
    fun provideApplication(): Application = application

    @AppScope
    @Provides
    fun provideApplicationContext(): Context = application.applicationContext

    @AppScope
    @Provides
    fun provideDeviceUuidFactory(): DeviceUuidFactory = DeviceUuidFactory(application)

    @AppScope
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://restaurant-buddy-server.herokuapp.com"

}