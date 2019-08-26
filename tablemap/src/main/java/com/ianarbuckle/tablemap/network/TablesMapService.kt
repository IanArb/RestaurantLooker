package com.ianarbuckle.tablemap.network

import com.ianarbuckle.tablemap.data.Tables
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
interface TablesMapService {

    @GET
    suspend fun fetchTablesById(@Query("id") id: String): Tables

    @PUT
    suspend fun updateTablesById(@Body tables: Tables): Tables
}