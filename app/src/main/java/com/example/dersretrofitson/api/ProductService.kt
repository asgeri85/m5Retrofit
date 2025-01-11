package com.example.dersretrofitson.api

import com.example.dersretrofitson.model.ProductResponseModelItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    fun getAllProducts(): Call<List<ProductResponseModelItem>>

    @GET("products/{id}")
    fun getProductDetail(@Path("id") id: String): Call<ProductResponseModelItem>

}