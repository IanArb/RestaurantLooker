package com.ianarbuckle.seathelper.home.builder

import com.ianarbuckle.seathelper.app.builder.AppComponent
import com.ianarbuckle.seathelper.home.HomeActivity
import dagger.Component

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
@HomeScope
@Component(modules = ([HomeModule::class]), dependencies = [(AppComponent::class)])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}