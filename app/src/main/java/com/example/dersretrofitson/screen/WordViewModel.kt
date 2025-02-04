package com.example.dersretrofitson.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dersretrofitson.model.WordModel
import com.example.dersretrofitson.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    val wordList = MutableLiveData<List<WordModel>>()

    fun getAllWord() {
        viewModelScope.launch {
            val list = productRepository.getAllWord()

            wordList.value = list
        }
    }

    fun addWord(wordModel: WordModel) {
        viewModelScope.launch {
            productRepository.addWord(wordModel)
        }
    }

}