package com.ianarbuckle.client

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.authentication.model.*
import com.ianarbuckle.authentication.repository.AuthenticationRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@ExperimentalCoroutinesApi
class AuthenticationClientTest {

    @Mock
    lateinit var repository: AuthenticationRepository

    @Mock
    lateinit var okHttpClient: OkHttpClient

    @Mock
    lateinit var uuidFactory: DeviceUuidFactory

    private lateinit var client: AuthenticationClient

    @Before
    fun setup() {
        initMocks(this)
        client = AuthenticationClientImpl(repository, okHttpClient, uuidFactory)
    }

    @Test
    fun `when authenicating user it should return token`() = runBlockingTest {
        whenever(uuidFactory.getUUID()).thenReturn("e00fdf4b-77b0-4f61-ad1d-4a051e460df5")
        val authBody = AuthBody("e00fdf4b-77b0-4f61-ad1d-4a051e460df5", "guest@mail.com", "password", false)
        val authenticationToken = AuthenticationToken("e00fdf4b-77b0-4f61-ad1d-4a051e460df5", "token")
        whenever(repository.authenticate(authBody)).thenReturn(authenticationToken)

        assertThat(client.authenticate(false)).isEqualTo(authenticationToken)
    }

    @Test
    fun `when registering user it should not be null`() = runBlockingTest {
        whenever(uuidFactory.getUUID()).thenReturn("e00fdf4b-77b0-4f61-ad1d-4a051e460df5")
        val roles = mutableSetOf<Role>(Role(UserRole.GUEST.name))
        val auth = Authentication(UserRole.GUEST.name, "e00fdf4b-77b0-4f61-ad1d-4a051e460df5", "guest@mail.com", "password", roles)

        whenever(repository.registerUser(auth)).thenReturn(Unit)

        assertThat(client.registerGuestUser()).isNotNull()
    }

    @Test
    fun `when retrieving user it should not be empty or null`() = runBlockingTest {
        whenever(uuidFactory.getUUID()).thenReturn("e00fdf4b-77b0-4f61-ad1d-4a051e460df5")

        val roles = mutableSetOf<Role>(Role("GUEST"))
        val user = User("guest", "e00fdf4b-77b0-4f61-ad1d-4a051e460df5",  "guest@mail.com", "password", true, roles)

        whenever(repository.retrieveUser("e00fdf4b-77b0-4f61-ad1d-4a051e460df5")).thenReturn(user)

        assertThat(client.retrieveUserByUuid()).isEqualTo(user)
    }

    @Test
    fun `when adding bearer token it should add to interceptor`() {
        val interceptor = Interceptor { chain ->
            val chainRequest = chain.request()

            val builder: Request.Builder = chainRequest.newBuilder()

            builder.header("Authorization", "Bearer token")

            val request = builder.method(chainRequest.method(), chainRequest.body())
                    .build()
            chain.proceed(request)
        }
        val interceptors = mutableListOf(interceptor)
        whenever(okHttpClient.interceptors()).thenReturn(interceptors)

        assertThat(client.addBearerToken("token")).isNotNull()
    }

}