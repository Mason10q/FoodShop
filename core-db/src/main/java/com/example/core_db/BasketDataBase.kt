package com.example.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(TegsConverter::class)
@Database(entities = [DishTable::class], version = 3, exportSchema = false)
abstract class BasketDataBase: RoomDatabase() {

    abstract fun getBasketDao(): BasketDao

}