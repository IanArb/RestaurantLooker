package com.ianarbuckle.database

import com.ianarbuckle.database.builder.DatabaseInjector

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
object DatabaseProvider {

    private lateinit var injector: DatabaseInjector

    fun init(injector: DatabaseInjector): DatabaseProvider {
        this.injector = injector
        return this
    }

    fun get(): DatabaseInjector = injector

}