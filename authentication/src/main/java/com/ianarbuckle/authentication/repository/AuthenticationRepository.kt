package com.ianarbuckle.authentication.repository

import com.ianarbuckle.authentication.factory.AuthenticationFactory
import com.ianarbuckle.authentication.model.AuthBody
import com.ianarbuckle.authentication.model.Authentication
import com.ianarbuckle.authentication.model.AuthenticationToken
import com.ianarbuckle.authentication.model.User
import kotlinx.coroutines.withTimeout

interface AuthenticationRepository {
    suspend fun authenticate(authBody: AuthBody): AuthenticationToken
    suspend fun registerUser(authentication: Authentication)
    suspend fun retrieveUser(uuid: String): User
}

class AuthenticationRepositoryImpl(private val retrofitFactory: AuthenticationFactory) : AuthenticationRepository {

    private val timeout: Long = 5_000

    override suspend fun authenticate(authBody: AuthBody): AuthenticationToken {
        return withTimeout(timeout) {
            getService().authenticate(authBody)
        }
    }

    override suspend fun registerUser(authentication: Authentication) =
            withTimeout(timeout) {
                getService().registerUser(authentication)
            }


    override suspend fun retrieveUser(uuid: String): User =
            withTimeout(timeout) {
                getService().retrieveUser(uuid)
            }

    private fun getService() = retrofitFactory.createService()
}