package com.example.dersretrofitson.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @BindingAdapter("load_image_api")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        imageView.loadUrl("https://cdn.pixabay.com/photo/2014/06/03/19/38/road-sign-361514_640.png")
    }

}

