package com.ianarbuckle.seathelper.app.builder

import com.ianarbuckle.seathelper.app.SeatHelperApplication
import dagger.Component

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
@SeatHelperScope
@Component(modules = [(SeatHelperAppModule::class)])
interface SeatHelperAppComponent {
    fun inject(seatHelperApplication: SeatHelperApplication)
}