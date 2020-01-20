package com.ianarbuckle.authentication.repository

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.authentication.factory.AuthenticationFactory
import com.ianarbuckle.authentication.model.*
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@ExperimentalCoroutinesApi
class AuthenticationRepositoryTest {

    @Mock
    lateinit var retrofitFactory: AuthenticationFactory

    lateinit var repository: AuthenticationRepository

    @Before
    fun setup() {
        initMocks(this)
        repository = AuthenticationRepositoryImpl(retrofitFactory)
    }

    @Test
    fun `when user is authenticated successfully then retrieve token`() = runBlockingTest {
        val fakeAuthenticationService = FakeAuthenticationService()
        whenever(retrofitFactory.createService()).thenReturn(fakeAuthenticationService)

        val authenticationToken = AuthenticationToken("guest@mail.com", "token")

        assertThat(repository.authenticate(AuthBody("guest@mail.com", "password", false))).isEqualTo(authenticationToken)
    }

    @Test(expected = TimeoutCancellationException::class)
    fun `when authenticating user timeouts it should throw timeout exception`() = runBlockingTest {
        val fakeAuthenticationService = SuspendingFakeService()
        whenever(retrofitFactory.createService()).thenReturn(fakeAuthenticationService)

        val deferred = async {
            repository.authenticate(AuthBody("guest@mail.com", "password", false))
        }

        advanceTimeBy(5_000)
        fakeAuthenticationService.deferredAuthenicate.complete(AuthenticationToken("guest@mail.com", "token"))

        deferred.await()
    }

    @Test
    fun `when user is registered successfully then create user does not return null`() = runBlockingTest {
        val fakeAuthenticationService = FakeAuthenticationService()
        whenever(retrofitFactory.createService()).thenReturn(fakeAuthenticationService)

        val roles = mutableSetOf(Role("GUEST"))
        val authentication = Authentication("guest@mail.com", "1234-1234-1234",  "guest@mail.com", "password", roles)

        assertThat(repository.registerUser(authentication)).isNotNull()
    }

    @Test(expected = TimeoutCancellationException::class)
    fun `when registering user timeouts it should throw timeout exception`() = runBlockingTest {
        val fakeAuthenticationService = SuspendingFakeService()
        whenever(retrofitFactory.createService()).thenReturn(fakeAuthenticationService)

        val deferred = async {
            val roles = mutableSetOf<Role>(Role("GUEST"))
            val authentication = Authentication("guest@mail.com", "1234-1234-1234", "guest@mail.com", "password", roles)
            repository.registerUser(authentication)
        }

        advanceTimeBy(5_000)
        fakeAuthenticationService.deferredRegister.complete(Unit)

        deferred.await()
    }

    @Test
    fun `when retrieving user it should not be empty or null`() = runBlockingTest {
        val fakeAuthenticationService = FakeAuthenticationService()
        whenever(retrofitFactory.createService()).thenReturn(fakeAuthenticationService)

        val roles = mutableSetOf(Role("GUEST"))
        val user = User("guest@mail.com", "1234-1234-1234", "guest@mail.com", "password", true, roles)

        assertThat(repository.retrieveUser("guest@mail.com")).isEqualTo(user)
    }

    @Test(expected = TimeoutCancellationException::class)
    fun `when retrieving user timeouts it should throw timeout exception`() = runBlockingTest {
        val fakeAuthenticationService = SuspendingFakeService()
        whenever(retrofitFactory.createService()).thenReturn(fakeAuthenticationService)

        val deferred = async {
            repository.retrieveUser("guest@mail.com")
        }

        advanceTimeBy(5_000)

        val roles = mutableSetOf(Role("GUEST"))
        val user = User("guest@mail.com", "1234-1234-1234", "guest@mail.com", "password", true, roles)

        fakeAuthenticationService.deferredUser.complete(user)

        deferred.await()
    }


}