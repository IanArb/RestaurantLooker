package com.ianarbuckle.tablemap.network.manager

import com.ianarbuckle.tablemap.network.RetrofitFactory
import com.ianarbuckle.tablemap.network.TablesMapService
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
interface ServiceManager {
    fun getService(): TablesMapService
}

class ServiceManagerImpl(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory) : ServiceManager {

    private lateinit var retrofitFactory: RetrofitFactory

    override fun getService(): TablesMapService {
        retrofitFactory = RetrofitFactory(baseUrl, okHttpClient, converterFactory)
        return retrofitFactory.createService()
    }
}