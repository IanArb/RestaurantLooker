package com.ianarbuckle.seathelper.network

import android.content.Context
import com.ianarbuckle.seathelper.app.builder.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by Ian Arbuckle on 15/07/2018.
 *
 */
@Module
class NetworkModule constructor(private val context: Context) {

    companion object {
        const val CACHE_SIZE: Long = 5 * 1024 * 1024
        const val TIMEOUT: Long = 10
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build()

    @AppScope
    @Provides
    fun provideCache(): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

}