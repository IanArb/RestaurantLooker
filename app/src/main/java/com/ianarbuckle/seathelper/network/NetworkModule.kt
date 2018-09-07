package com.ianarbuckle.seathelper.network

import android.content.Context
import com.ianarbuckle.seathelper.app.builder.RestaurantBuddyAppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by Ian Arbuckle on 15/07/2018.
 *
 */
@Module
class NetworkModule constructor(val context: Context) {

    companion object {
        const val CACHE_SIZE: Long = 5 * 1024 * 1024
        const val TIMEOUT: Long = 10
    }

    @RestaurantBuddyAppScope
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .cache(cache)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build()

    @RestaurantBuddyAppScope
    @Provides
    fun provideInterceptor(): Interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d(it) })
            .apply { level = HttpLoggingInterceptor.Level.BASIC }

    @RestaurantBuddyAppScope
    @Provides
    fun provideCache(): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

}