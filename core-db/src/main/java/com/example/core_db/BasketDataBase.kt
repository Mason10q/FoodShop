package com.example.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_entities.Dish

@Database(entities = [Dish::class], version = 1, exportSchema = true)
abstract class BasketDataBase: RoomDatabase() {

    abstract fun getBasketDao(): BasketDao

}