package com.example.dersretrofitson.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponseModelItem(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("title")
    val title: String?
):Parcelable