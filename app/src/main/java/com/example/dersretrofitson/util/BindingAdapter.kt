package com.example.dersretrofitson.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @BindingAdapter("load_image_api")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        imageView.loadUrl(url)
    }

}

