package com.example.dersretrofitson.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dersretrofitson.model.WordModel

@Database(
    entities = [WordModel::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductsDAO

}