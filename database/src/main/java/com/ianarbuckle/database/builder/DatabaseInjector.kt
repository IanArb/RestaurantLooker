package com.ianarbuckle.database.builder

import android.content.Context
import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.database.database.builder.DaggerDatabaseComponent
import com.ianarbuckle.database.database.builder.DatabaseModule

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
interface DatabaseInjector {
    fun inject(): DatabaseClient
}

class DatabaseInjectorImpl(private val context: Context) : DatabaseInjector {

    override fun inject(): DatabaseClient {
       return DaggerDatabaseComponent.builder()
                .databaseModule(DatabaseModule((context)))
                .build()
               .databaseClient()
    }
}