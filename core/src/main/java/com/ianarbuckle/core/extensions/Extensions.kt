package com.ianarbuckle.core.extensions

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.RequiresPermission
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ianarbuckle.core.R

/**
 * Created by Ian Arbuckle on 2019-07-07.
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

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
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

fun Context.getColorFromAttr(
        @AttrRes attrColor: Int,
        typedValue: TypedValue = TypedValue(),
        resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}