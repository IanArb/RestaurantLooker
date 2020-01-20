package com.ianarbuckle.seathelper.splash.core.presenter

import com.ianarbuckle.authentication.model.AuthenticationToken
import com.ianarbuckle.authentication.model.User
import com.ianarbuckle.seathelper.splash.core.interactor.SplashInteractor
import com.ianarbuckle.seathelper.splash.core.router.SplashRouter
import com.ianarbuckle.seathelper.splash.core.view.SplashView
import com.ianarbuckle.seathelper.utils.CoroutineTestRule
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@ExperimentalCoroutinesApi
class SplashPresenterTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var interactor: SplashInteractor

    @Mock
    lateinit var view: SplashView

    @Mock
    lateinit var router: SplashRouter

    lateinit var presenter: SplashPresenter

    @Before
    fun setup() {
        initMocks(this)
        presenter = SplashPresenterImpl(view, interactor, router)
    }

    @Test
    fun `when onCreate it should authenticate user if it exists`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(interactor.getGuestUser()).thenReturn(User("GUEST", "guest@mail.com", "password", true))
        whenever(interactor.authenticate()).thenReturn(AuthenticationToken("guest", "token"))

        presenter.onCreate()

        verify(interactor, times(1)).addBearerToken("token")
        verify(router, times(1)).navigateToHomeScreen()
    }

    @Test
    fun `when onCreate it should return error if user doesn't exist or not enabled`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(interactor.getGuestUser()).thenReturn(User())
        whenever(interactor.getGuestUser().isEnabled).thenReturn(false)
        whenever(interactor.authenticate()).thenReturn(AuthenticationToken("guest", "token"))

        presenter.onCreate()

        verify(interactor, times(1)).addBearerToken("token")
        verify(router, times(1)).navigateToHomeScreen()
    }

}