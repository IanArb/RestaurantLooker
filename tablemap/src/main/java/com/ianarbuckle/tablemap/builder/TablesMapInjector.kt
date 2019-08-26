package com.ianarbuckle.tablemap.builder

import com.ianarbuckle.tablemap.network.builder.NetworkModule
import com.ianarbuckle.tablemap.ui.TablesMapActivity
import com.ianarbuckle.tablemap.ui.core.builder.DaggerTablesMapComponent
import com.ianarbuckle.tablemap.ui.core.builder.TablesMapModule
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
interface TablesMapInjector {
    fun inject(activity: TablesMapActivity)
}

class TablesMapInjectorImpl(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory) : TablesMapInjector {

    override fun inject(activity: TablesMapActivity) {
        DaggerTablesMapComponent.builder()
                .tablesMapModule(TablesMapModule(activity))
                .networkModule(NetworkModule(okHttpClient, baseUrl, converterFactory))
                .build()
                .inject(activity)
    }

}