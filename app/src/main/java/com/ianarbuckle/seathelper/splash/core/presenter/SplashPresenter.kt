package com.ianarbuckle.seathelper.splash.core.presenter

import com.ianarbuckle.core.utils.DispatcherProvider
import com.ianarbuckle.core.utils.DispatchersProviderImpl
import com.ianarbuckle.seathelper.splash.core.interactor.SplashInteractor
import com.ianarbuckle.seathelper.splash.core.router.SplashRouter
import com.ianarbuckle.seathelper.splash.core.view.SplashView
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
interface SplashPresenter {
    fun onCreate()
    fun onDestroy()
}

class SplashPresenterImpl(private val view: SplashView,
                          private val interactor: SplashInteractor,
                          private val router: SplashRouter,
                          private val dispatchers: DispatcherProvider = DispatchersProviderImpl()) : SplashPresenter, CoroutineScope {

    private var job: Job  = Job()

    override val coroutineContext: CoroutineContext
        get() = job + dispatchers.main()

    override fun onCreate() {
        job = launch {
            try {
                withContext(dispatchers.io()) {
                    val user = interactor.getGuestUser()
                    user?.let {
                        it.uuid?.let { uuid ->
                            if (uuid.isNotEmpty()) {
                                authenticate()
                            } else {
                                interactor.registerGuestUser()
                                authenticate()
                            }
                        }
                    }
                }
            } catch (exception: Exception) {
                exception as HttpException
                if (exception.code() == 404) {
                    withContext(dispatchers.io()) {
                        interactor.registerGuestUser()
                        authenticate()
                    }
                }
                view.showErrorToast()
                router.navigateToHomeScreen()
            } finally {
                router.navigateToHomeScreen()
            }
        }
    }

    private suspend fun authenticate() {
        val (username, token) = interactor.authenticate()
        if (token.isNotEmpty()) {
            interactor.addBearerToken(token)
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}