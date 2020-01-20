package com.ianarbuckle.authentication.repository

import com.ianarbuckle.authentication.model.*
import com.ianarbuckle.authentication.service.AuthenticationService

class FakeAuthenticationService : AuthenticationService {

    private val roles = mutableSetOf(Role("GUEST"))

    override suspend fun registerUser(authentication: Authentication) {
        Authentication("guest@mail.com", "1234-1234-1234", "guest@mail.com", "password", roles)
    }

    override suspend fun authenticate(authBody: AuthBody): AuthenticationToken {
        return AuthenticationToken("guest@mail.com", "token")
    }

    override suspend fun retrieveUser(email: String): User {
        return User("guest@mail.com", "1234-1234-1234", "guest@mail.com", "password", true, roles)
    }
}