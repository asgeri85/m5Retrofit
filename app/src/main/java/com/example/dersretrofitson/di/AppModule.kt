package com.example.dersretrofitson.di

import com.example.dersretrofitson.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val BASE_URL = "https://fakestoreapi.com/"

    @Singleton
    @Provides
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideService(okHttpClient: OkHttpClient): ProductService {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(ProductService::class.java)
    }
}