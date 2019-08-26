package com.ianarbuckle.tablemap

import com.ianarbuckle.tablemap.builder.TablesMapInjector
import com.ianarbuckle.tablemap.builder.TablesMapInjectorImpl
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
data class TablesMapInitializer(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val converterFactory: Converter.Factory) {

    fun init() {
        val injector: TablesMapInjector = TablesMapInjectorImpl(okHttpClient, baseUrl, converterFactory)
        TablesMapProvider.init(injector)
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var converterFactory: Converter.Factory

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }
        fun withConverterFactory(converterFactory: Converter.Factory) = apply { this.converterFactory = converterFactory }

        fun build() {
            val initializer = TablesMapInitializer(baseUrl, okHttpClient, converterFactory)
            initializer.init()
        }
    }
}