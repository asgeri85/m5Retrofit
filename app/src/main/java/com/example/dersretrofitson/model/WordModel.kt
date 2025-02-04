package com.example.dersretrofitson.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordModel(
    val turk: String,
    val english: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
