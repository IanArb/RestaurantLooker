package com.ianarbuckle.authentication.repository

import com.ianarbuckle.authentication.model.*
import com.ianarbuckle.authentication.service.AuthenticationService
import kotlinx.coroutines.CompletableDeferred

class SuspendingFakeService : AuthenticationService {

    val deferredAuthenicate = CompletableDeferred<AuthenticationToken>()
    val deferredRegister = CompletableDeferred<Unit>()
    val deferredUser = CompletableDeferred<User>()

    private val roles = mutableSetOf<Role>(Role("GUEST"))

    override suspend fun registerUser(authentication: Authentication) {
        deferredRegister.await()
    }

    override suspend fun authenticate(authBody: AuthBody): AuthenticationToken = deferredAuthenicate.await()

    override suspend fun retrieveUser(email: String): User {
        return deferredUser.await()
    }
}