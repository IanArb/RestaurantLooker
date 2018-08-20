package com.ianarbuckle.retrofitclient

import retrofit2.Retrofit

/**
 * Created by Ian Arbuckle on 16/07/2018.
 *
 */
interface RetrofitFactory {
    fun getRetrofit(): Retrofit
}