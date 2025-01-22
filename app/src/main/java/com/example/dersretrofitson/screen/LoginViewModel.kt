package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val userLogin = MutableLiveData<Boolean>()
    val userRegister = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val isUserAuth = MutableLiveData<Boolean>()

    fun loginFirebase(email: String, password: String) {
        loading.value = true
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { response ->
            userLogin.value = response.isSuccessful
            loading.value = false
        }
    }

    fun registerFirebase(email: String, password: String) {
        loading.value = true
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { response ->
                userRegister.value = response.isSuccessful
                loading.value = false
            }
    }

    fun getUser() {
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            isUserAuth.value = true
        } else {
            isUserAuth.value = false
        }
    }
}