package com.ianarbuckle.tablemap.network.builder

import com.ianarbuckle.tablemap.network.manager.ServiceManager
import com.ianarbuckle.tablemap.network.manager.ServiceManagerImpl
import com.ianarbuckle.tablemap.ui.core.builder.TablesMapScope
import com.ianarbuckle.tablemap.ui.core.repository.TablesMapRepository
import com.ianarbuckle.tablemap.ui.core.repository.TablesMapRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
@Module
class NetworkModule(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory) {

    @TablesMapScope
    @Provides
    fun provideServiceManager(): ServiceManager = ServiceManagerImpl(okHttpClient, baseUrl, converterFactory)

}