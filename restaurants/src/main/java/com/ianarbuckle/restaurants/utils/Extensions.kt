package com.ianarbuckle.restaurants.utils

import android.content.Context
import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ianarbuckle.restaurants.R

/**
 * Created by Ian Arbuckle on 25/08/2018.
 *
 */
fun ImageView.provideImage(context: Context, imageUrl: String) {
    val requestOptions = RequestOptions()
    requestOptions.apply {
        placeholder(R.drawable.ic_broken_image)
        error(R.drawable.ic_error)
    }
    Glide.with(context)
            .load(imageUrl)
            .apply(requestOptions)
            .into(this)
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String) = provideImage(context, imageUrl)

@BindingAdapter("statusColor")
fun TextView.setStatusColor(status: String) {
    when(status) {
        "OPEN" -> setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
        "CLOSED" -> setTextColor(ContextCompat.getColor(context, R.color.colorRed))
    }
}
