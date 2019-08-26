package com.ianarbuckle.restaurants.utils

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ianarbuckle.core.extensions.provideImage
import com.ianarbuckle.restaurant.R

/**
 * Created by Ian Arbuckle on 25/08/2018.
 *
 */

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String) = provideImage(context, imageUrl)

@BindingAdapter("statusColor")
fun TextView.setStatusColor(status: String) {
    when(status) {
        "OPEN" -> setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
        "CLOSED" -> setTextColor(ContextCompat.getColor(context, R.color.colorRed))
    }
}


