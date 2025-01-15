package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dersretrofitson.api.RetrofitClient
import com.example.dersretrofitson.model.ProductResponseModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val productList: MutableLiveData<List<ProductResponseModelItem>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    val categoryList: MutableLiveData<List<String>> = MutableLiveData()

    val api = RetrofitClient()

    init {
        getProducts()
        getAllCategory()
    }

    fun getProducts() {
        loading.value = true
        api.createApi().getAllProducts().enqueue(object : Callback<List<ProductResponseModelItem>> {
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
        api.createApi().getCategories().enqueue(object : Callback<List<String>> {
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
        api.createApi().getCategoryProduct(categoryName)
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

}