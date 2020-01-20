package com.ianarbuckle.authentication.service

import com.ianarbuckle.authentication.model.AuthBody
import com.ianarbuckle.authentication.model.Authentication
import com.ianarbuckle.authentication.model.AuthenticationToken
import com.ianarbuckle.authentication.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationService {

    @POST("authentication/register")
    suspend fun registerUser(@Body authentication: Authentication)

    @POST("authentication/login")
    suspend fun authenticate(@Body authBody: AuthBody): AuthenticationToken

    @GET("authentication/retrieveUser")
    suspend fun retrieveUser(@Query(value = "uuid") email: String): User
}