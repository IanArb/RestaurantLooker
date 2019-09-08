package com.ianarbuckle.booking.ui.phonePrefix.core.interactor

import android.app.Activity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.ianarbuckle.booking.R
import com.ianarbuckle.models.Country


/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
interface PhonePrefixInteractor {
    fun getCountries(): MutableList<Country>
}

class PhonePrefixInteractorImpl(private val activity: Activity) : PhonePrefixInteractor {

    override fun getCountries(): MutableList<Country> {
        val json = activity.resources.openRawResource(R.raw.countries).bufferedReader().use { it.readText() }

        val listType = object : TypeToken<List<Country>>() {}.type
        return GsonBuilder().create().fromJson(json, listType)
    }
}