package com.ianarbuckle.restaurants.utils

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresPermission
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ianarbuckle.restaurant.R
import java.util.jar.Manifest

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

@RequiresPermission(value = ACCESS_NETWORK_STATE)
fun Context.isConnected(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let { networkInfo ->
            if (networkInfo.isConnected) return true
        }
    }
    return false
}


