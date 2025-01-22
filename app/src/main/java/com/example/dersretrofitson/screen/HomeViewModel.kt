package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dersretrofitson.api.ProductService
import com.example.dersretrofitson.model.ProductResponseModelItem
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val api: ProductService,
    val okHttpClient: OkHttpClient,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val productList: MutableLiveData<List<ProductResponseModelItem>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    val categoryList: MutableLiveData<List<String>> = MutableLiveData()


    init {
        getProducts()
        getAllCategory()
    }

    fun getProducts() {
        loading.value = true
        api.getAllProducts().enqueue(object : Callback<List<ProductResponseModelItem>> {
            override fun onResponse(
                call: Call<List<ProductResponseModelItem>>,
                response: Response<List<ProductResponseModelItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        productList.value = data
                    }
                }
                loading.value = false
            }

            override fun onFailure(call: Call<List<ProductResponseModelItem>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }

    fun getAllCategory() {
        api.getCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        categoryList.value = data
                    }
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {

            }

        })
    }

    fun getCategoryProduct(categoryName: String) {
        api.getCategoryProduct(categoryName)
            .enqueue(object : Callback<List<ProductResponseModelItem>> {
                override fun onResponse(
                    call: Call<List<ProductResponseModelItem>>,
                    response: Response<List<ProductResponseModelItem>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            productList.value = data
                        }
                    }
                    loading.value = false
                }

                override fun onFailure(call: Call<List<ProductResponseModelItem>>, t: Throwable) {
                    error.value = t.localizedMessage
                }
            })
    }

    fun logoutFirebase() {
        firebaseAuth.signOut()
    }

}