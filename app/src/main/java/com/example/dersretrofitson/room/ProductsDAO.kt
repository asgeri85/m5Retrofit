package com.example.dersretrofitson.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dersretrofitson.model.WordModel

@Dao
interface ProductsDAO {

    @Insert
    suspend fun insertWord(word: WordModel)

    @Query("Select * From wordmodel")
    suspend fun getAllWords(): List<WordModel>

}