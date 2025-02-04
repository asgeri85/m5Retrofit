package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dersretrofitson.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    val userLogin = MutableLiveData<Boolean>()
    val userRegister = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val isUserAuth = MutableLiveData<Boolean>()

    fun loginFirebase(email: String, password: String) {
        viewModelScope.launch {
            val result = productRepository.loginFirebase(email, password)
            userLogin.value = result.user?.uid != null
        }
    }

    fun registerFirebase(email: String, password: String) {
        loading.value = true

        viewModelScope.launch {
            val result = productRepository.registerFirebase(email, password)

            userRegister.value = result.user?.uid != null
        }
    }

    fun getUser() {
        /* val userId = firebaseAuth.currentUser?.uid

         if (userId != null) {
             isUserAuth.value = true
         } else {
             isUserAuth.value = false
         }*/
    }
}