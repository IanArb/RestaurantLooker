package com.ianarbuckle.restaurants.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Ian Arbuckle on 05/08/2018.
 *
 */

fun provideImage(context: Context, imageUrl: String, imageView: ImageView) {
    Glide.with(context)
            .load(imageUrl)
            .into(imageView)
}
