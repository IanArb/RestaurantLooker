package com.ianarbuckle.database

import android.content.Context
import com.ianarbuckle.database.builder.DatabaseInjector
import com.ianarbuckle.database.builder.DatabaseInjectorImpl
import com.ianarbuckle.database.client.DatabaseClient

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
data class DatabaseInitialiser(private val context: Context) {

    fun init(): DatabaseClient {
        val injector: DatabaseInjector = DatabaseInjectorImpl(context)
        return injector.inject()
    }

    class Builder {
        private lateinit var context: Context

        fun withContext(context: Context) = apply { this.context = context }

        fun build(): DatabaseClient {
            val initializer = DatabaseInitialiser(context)
            return initializer.init()
        }
    }
}