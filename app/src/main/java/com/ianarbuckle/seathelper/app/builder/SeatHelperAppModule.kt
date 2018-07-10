package com.ianarbuckle.seathelper.app.builder

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
@Module
class SeatHelperAppModule constructor(private val application: Application) {

    @SeatHelperScope
    @Provides
    fun provideApplication(): Application = application

    @SeatHelperScope
    @Provides
    fun provideApplicationContext(): Context = application.applicationContext

}