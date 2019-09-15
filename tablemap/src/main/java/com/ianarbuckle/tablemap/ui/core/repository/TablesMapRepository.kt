package com.ianarbuckle.tablemap.ui.core.repository

import com.ianarbuckle.tablemap.data.Tables
import com.ianarbuckle.tablemap.network.manager.ServiceManager

/**
 * Created by Ian Arbuckle on 2019-07-31.
 *
 */
interface TablesMapRepository {
    suspend fun fetchTablesById(id: String): Tables
}

class TablesMapRepositoryImpl(private val serviceManager: ServiceManager) : TablesMapRepository {

    override suspend fun fetchTablesById(id: String): Tables = serviceManager.getService().fetchTablesById(id)
}