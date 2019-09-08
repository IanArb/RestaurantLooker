package com.ianarbuckle.booking.ui.phonePrefix.core.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.databinding.PhonePrefixItemBinding
import com.ianarbuckle.models.Country
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
class PhonePrefixAdapter(private val countries: MutableList<Country>) : RecyclerView.Adapter<PhonePrefixAdapter.PhonePrefixViewHolder>() {

    private var isFiltering = false
    private var filteredCountries = ArrayList<Country>()

    var clickListener: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonePrefixViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: PhonePrefixItemBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.phone_prefix_item, parent, false)
        return PhonePrefixViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(isFiltering) {
            filteredCountries.size
        } else {
            countries.size
        }
    }

    override fun onBindViewHolder(holder: PhonePrefixViewHolder, position: Int) {
        val items = if(isFiltering) filteredCountries[position] else countries[position]
        holder.binding.country = items
        holder.onClickListener(items)
    }

    fun filterCountries(filterValue: String) {
        isFiltering = true
        val newCountries = countries.filter {
            val countryName = it.countryName.toLowerCase(Locale.getDefault())
            val value = filterValue.toLowerCase(Locale.getDefault())
            countryName.contains(value)
        } as ArrayList<Country>

        DiffUtil.calculateDiff(CountryDiffUtilsCallback(countries, newCountries)).dispatchUpdatesTo(this)
        filteredCountries = newCountries
    }

    inner class PhonePrefixViewHolder(val binding: PhonePrefixItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onClickListener(country: Country) {
            binding.root.setOnClickListener { clickListener?.invoke(country) }
        }
    }
}