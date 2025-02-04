package com.example.dersretrofitson.repository

import com.example.dersretrofitson.api.ProductService
import com.example.dersretrofitson.model.WordModel
import com.example.dersretrofitson.room.ProductsDAO
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productService: ProductService,
    private val firebaseAuth: FirebaseAuth,
    private val productsDAO: ProductsDAO
) {

    suspend fun getProducts() = productService.getAllProducts()

    suspend fun getCategories() = productService.getCategories()

    suspend fun loginFirebase(email: String, password: String): AuthResult =
        firebaseAuth.signInWithEmailAndPassword(email, password).await()

    suspend fun registerFirebase(email: String, password: String): AuthResult =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

    suspend fun addWord(wordModel: WordModel) = productsDAO.insertWord(wordModel)

    suspend fun getAllWord(): List<WordModel> = productsDAO.getAllWords()

}