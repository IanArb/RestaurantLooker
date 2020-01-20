package com.ianarbuckle.client

import com.ianarbuckle.authentication.model.*
import com.ianarbuckle.authentication.repository.AuthenticationRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

enum class UserRole {
    GUEST,
    REGISTERED
}

interface AuthenticationClient {
    suspend fun authenticate(shouldRefresh: Boolean = false): AuthenticationToken
    suspend fun registerGuestUser()
    suspend fun retrieveUserByUuid(): User?
    fun addBearerToken(token: String)
}

class AuthenticationClientImpl(private val repository: AuthenticationRepository,
                               private val okHttpClient: OkHttpClient,
                               private val uuidFactory: DeviceUuidFactory) : AuthenticationClient {

    override suspend fun authenticate(shouldRefresh: Boolean): AuthenticationToken {
        val authBody = AuthBody(uuidFactory.getUUID(), "guest@mail.com", "password", shouldRefresh)
        return repository.authenticate(authBody)
    }

    override suspend fun registerGuestUser() {
        val roles = mutableSetOf(Role(UserRole.GUEST.name))
        val authentication = uuidFactory.getUUID()?.let { Authentication(UserRole.GUEST.name, it, "guest@mail.com", "password", roles) }
        authentication?.let { repository.registerUser(it) }
    }

    override suspend fun retrieveUserByUuid(): User? = uuidFactory.getUUID()?.let { repository.retrieveUser(it) }

    override fun addBearerToken(token: String) {
        val interceptor = Interceptor { chain ->
            val chainRequest = chain.request()

            val builder: Request.Builder = chainRequest.newBuilder()

            builder.header("Authorization", "Bearer $token")

            val request = builder.method(chainRequest.method(), chainRequest.body())
                    .build()
            chain.proceed(request)
        }
        okHttpClient.interceptors().add(interceptor)
    }
}