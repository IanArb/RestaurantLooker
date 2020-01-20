package com.ianarbuckle.seathelper.splash.core.interactor

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.authentication.model.*
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.client.UserRole
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@ExperimentalCoroutinesApi
class SplashInteractorTest {

    @Mock
    lateinit var client: AuthenticationClient

    lateinit var interactor: SplashInteractor

    private val argumentCaptor = argumentCaptor<() -> Unit>()

    @Before
    fun setup() {
        initMocks(this)

        interactor = SplashInteractorImpl(client)
    }

    @Test
    fun `when authenticating user it should return auth token`() = runBlockingTest {
        val authToken = AuthenticationToken("e00fdf4b-77b0-4f61-ad1d-4a051e460df5", "token")
        whenever(client.authenticate(false)).thenReturn(authToken)

        assertThat(interactor.authenticate()).isEqualTo(authToken)
    }

    @Test
    fun `when retrieving user it should return user`() = runBlockingTest {
        val user = User(UserRole.GUEST.name, "e00fdf4b-77b0-4f61-ad1d-4a051e460df5", "guest@mail.com", "password", true)
        whenever(client.retrieveUserByUuid()).thenReturn(user)

        assertThat(interactor.getGuestUser()).isEqualTo(user)
    }

    @Test
    fun `when registered user it should not be null`() = runBlockingTest {
        val roles = setOf(Role("GUEST"))
        val auth = Authentication(UserRole.GUEST.name, "e00fdf4b-77b0-4f61-ad1d-4a051e460df5", "guest@mail.com", "password", roles)
        client.registerGuestUser()

        assertThat(interactor.addBearerToken("token")).isNotNull()
    }

    @Test
    fun `when authenticated it should add bearer token`() {
        client.addBearerToken("token")

        assertThat(interactor.addBearerToken("token")).isNotNull()
    }


}