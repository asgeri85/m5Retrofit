package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dersretrofitson.model.ProductResponseModelItem
import com.example.dersretrofitson.repository.ProductRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
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
        viewModelScope.launch {
            try {
                val response = productRepository.getProducts()
                val list = productRepository.getAllWord()

                if (response.isSuccessful) {
                    productList.value = response.body()
                }

            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }

    fun getAllCategory() {
        viewModelScope.launch {
            try {
                val response = productRepository.getCategories()

                if (response.isSuccessful) {
                    categoryList.value = response.body()
                }
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }

    fun getCategoryProduct(categoryName: String) {
        /*  api.getCategoryProduct(categoryName)
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
              })*/
    }

    fun logoutFirebase() {
        firebaseAuth.signOut()
    }

}