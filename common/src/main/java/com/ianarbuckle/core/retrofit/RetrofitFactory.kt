package com.ianarbuckle.core.retrofit

import retrofit2.Retrofit

/**
 * Created by Ian Arbuckle on 16/07/2018.
 *
 */
interface RetrofitFactory {
    fun getRetrofit(): Retrofit
}