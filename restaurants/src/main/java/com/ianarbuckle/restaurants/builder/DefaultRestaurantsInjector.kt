package com.ianarbuckle.restaurants.builder


import com.ianarbuckle.restaurants.ui.home.RestaurantsFragment
import com.ianarbuckle.restaurants.ui.home.builder.DaggerHomeComponent
import com.ianarbuckle.restaurants.ui.home.builder.HomeModule
import com.ianarbuckle.restaurants.network.builder.NetworkModule
import com.ianarbuckle.restaurants.RestaurantsNavigator
import com.ianarbuckle.restaurants.ui.menu.MenuActivity
import com.ianarbuckle.restaurants.ui.menu.builder.DaggerMenuComponent
import com.ianarbuckle.restaurants.ui.menu.builder.MenuModule
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 18/07/2018.
 *
 */
class DefaultRestaurantsInjector(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory, private val navigator: RestaurantsNavigator) : RestaurantsInjector {

    override fun inject(fragment: RestaurantsFragment) {
        DaggerHomeComponent.builder()
                .homeModule(HomeModule(fragment, navigator))
                .networkModule(NetworkModule(okHttpClient, baseUrl, converterFactory))
                .build()
                .inject(fragment)
    }

    override fun inject(activity: MenuActivity) {
        DaggerMenuComponent.builder()
                .menuModule(MenuModule(activity))
                .build()
                .inject(activity)
    }
}