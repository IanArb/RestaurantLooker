package com.ianarbuckle.authentication.factory

import com.ianarbuckle.authentication.service.AuthenticationService
import com.ianarbuckle.core.retrofit.DefaultRetrofitFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class AuthenticationFactory(baseUrl: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory) : DefaultRetrofitFactory(baseUrl, okHttpClient, converterFactory) {

    override fun modifyRetrofit(builder: Retrofit.Builder): Retrofit.Builder {
        return super.modifyRetrofit(builder.addCallAdapterFactory(CoroutineCallAdapterFactory()))
    }

    fun createService(): AuthenticationService = getRetrofit().create(AuthenticationService::class.java)
}