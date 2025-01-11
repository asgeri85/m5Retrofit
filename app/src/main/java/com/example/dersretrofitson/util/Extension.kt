package com.example.dersretrofitson.util

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.visibleItem() {
    this.visibility = View.VISIBLE
}

fun View.goneItem() {
    this.visibility = View.GONE
}

fun ImageView.loadUrl(url: String) {
    Picasso.get().load(url).into(this)
}