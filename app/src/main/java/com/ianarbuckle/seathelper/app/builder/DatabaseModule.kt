package com.ianarbuckle.seathelper.app.builder

import android.content.Context
import com.ianarbuckle.database.DatabaseInitialiser
import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.database.databaseInitializer
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
@Module
class DatabaseModule(private val context: Context) {

    @AppScope
    @Provides
    fun provideDatabaseClient(): DatabaseClient = databaseInitializer {
        withContext(context)
    }
}