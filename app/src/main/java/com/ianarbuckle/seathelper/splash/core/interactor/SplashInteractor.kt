package com.ianarbuckle.seathelper.splash.core.interactor

import com.ianarbuckle.authentication.model.*
import com.ianarbuckle.client.AuthenticationClient
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
interface SplashInteractor {
    suspend fun authenticate(): AuthenticationToken
    suspend fun registerGuestUser()
    fun addBearerToken(token: String)
    suspend fun getGuestUser(): User?
}

class SplashInteractorImpl(private val authenticationClient: AuthenticationClient) : SplashInteractor {

    override suspend fun authenticate(): AuthenticationToken = authenticationClient.authenticate()

    override fun addBearerToken(token: String) = authenticationClient.addBearerToken(token)

    override suspend fun registerGuestUser() = authenticationClient.registerGuestUser()

    override suspend fun getGuestUser(): User? = authenticationClient.retrieveUserByUuid()

}