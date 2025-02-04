package com.example.dersretrofitson.api

import com.example.dersretrofitson.model.ProductResponseModelItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponseModelItem>>

    @GET("products/{id}")
    fun getProductDetail(@Path("id") id: String): Call<ProductResponseModelItem>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products/category/{category}")
    fun getCategoryProduct(@Path("category") category: String): Call<List<ProductResponseModelItem>>

}