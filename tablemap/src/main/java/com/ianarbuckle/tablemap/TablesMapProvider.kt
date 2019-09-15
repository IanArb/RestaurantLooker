package com.ianarbuckle.tablemap

import com.ianarbuckle.tablemap.builder.TablesMapInjector

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
object TablesMapProvider {

    private lateinit var injector: TablesMapInjector

    fun init(injector: TablesMapInjector): TablesMapProvider {
        this.injector = injector
        return this
    }

    fun get(): TablesMapInjector {
        return injector
    }


}