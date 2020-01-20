package com.ianarbuckle.authentication.builder

import com.ianarbuckle.authentication.factory.AuthenticationFactory
import com.ianarbuckle.authentication.repository.AuthenticationRepository
import com.ianarbuckle.authentication.repository.AuthenticationRepositoryImpl
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.client.AuthenticationClientImpl
import com.ianarbuckle.core.utils.DeviceUuidFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationScope

@Module
class AuthenticationModule(private val baseUrl: String,
                           private val okHttpClient: OkHttpClient,
                           private val converterFactory: Converter.Factory,
                           private val uuidFactory: DeviceUuidFactory) {

    @AuthenticationScope
    @Provides
    fun provideRepository(authenticationFactory: AuthenticationFactory): AuthenticationRepository = AuthenticationRepositoryImpl(authenticationFactory)

    @AuthenticationScope
    @Provides
    fun provideRetrofitFactory(): AuthenticationFactory = AuthenticationFactory(baseUrl, okHttpClient, converterFactory)

    @AuthenticationScope
    @Provides
    fun provideClient(repository: AuthenticationRepository): AuthenticationClient = AuthenticationClientImpl(repository, okHttpClient, uuidFactory)
}

@AuthenticationScope
@Component(modules = [AuthenticationModule::class])
interface AuthenticationComponent {
    fun client(): AuthenticationClient
}