package com.ianarbuckle.tablemap.ui.core.interactor

import android.app.Activity
import com.ianarbuckle.tablemap.data.Tables
import com.ianarbuckle.tablemap.ui.core.repository.TablesMapRepository

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
interface TablesMapInteractor {
    suspend fun fetchTablesById(): Tables
}

class TablesMapInteractorImpl(private val repository: TablesMapRepository, private val activity: Activity) : TablesMapInteractor {

    override suspend fun fetchTablesById(): Tables = repository.fetchTablesById(activity.intent.getStringExtra("id"))
}