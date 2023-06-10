package com.example.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core_entities.Dish
import com.example.core_entities.TegsConverter

@TypeConverters(TegsConverter::class)
@Database(entities = [Dish::class], version = 2, exportSchema = false)
abstract class BasketDataBase: RoomDatabase() {

    abstract fun getBasketDao(): BasketDao

}