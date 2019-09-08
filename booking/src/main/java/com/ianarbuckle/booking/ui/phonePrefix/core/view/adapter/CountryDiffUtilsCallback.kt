package com.ianarbuckle.booking.ui.phonePrefix.core.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ianarbuckle.models.Country

/**
 * Created by Ian Arbuckle on 2019-09-05.
 *
 */
class CountryDiffUtilsCallback(private val oldList: MutableList<Country>, private val newList: MutableList<Country>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
            = oldList[oldItemPosition].countryName == newList[newItemPosition].countryName

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRow = oldList[oldItemPosition]
        val newRow = newList[newItemPosition]
        return oldRow == newRow
    }
}