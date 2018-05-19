package com.ianarbuckle.seathelper.app

import android.app.Application
import com.ianarbuckle.seathelper.app.builder.DaggerSeatHelperAppComponent
import com.ianarbuckle.seathelper.app.builder.SeatHelperAppComponent
import com.ianarbuckle.seathelper.app.builder.SeatHelperAppModule

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class SeatHelperApplication : Application() {

    companion object {
        lateinit var component: SeatHelperAppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerSeatHelperAppComponent.builder()
                .seatHelperAppModule(SeatHelperAppModule(this))
                .build()
        component.inject(this)
    }
}