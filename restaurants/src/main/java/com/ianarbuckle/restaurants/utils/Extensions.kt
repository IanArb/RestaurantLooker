package com.ianarbuckle.restaurants.utils

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ianarbuckle.core.extensions.getColorFromAttr
import com.ianarbuckle.core.extensions.provideImage
import com.ianarbuckle.restaurant.R

/**
 * Created by Ian Arbuckle on 25/08/2018.
 *
 */

@BindingAdapter("statusColor")
fun TextView.setStatusColor(status: String) {
    val errorColor = context.getColorFromAttr(R.attr.colorError)
    val successColor = context.getColorFromAttr(R.attr.colorSuccess)
    when(status) {
        "OPEN" -> setTextColor(successColor)
        "CLOSED" -> setTextColor(errorColor)
    }
}


