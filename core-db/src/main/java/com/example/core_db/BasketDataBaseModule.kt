package com.example.core_db

import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context

@Module
class BasketDataBaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideBasketDataBase() = Room.databaseBuilder(
        context.applicationContext,
        BasketDataBase::class.java,
        "basket_database"
    ).build()

    @Provides
    fun provideDao(dataBase: BasketDataBase) = dataBase.getBasketDao()

    @Provides
    fun provideRepository(dao: BasketDao) = BasketRepo(dao)

}