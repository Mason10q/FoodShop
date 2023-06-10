package com.example.core_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_entities.Dish
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDish(dish: Dish): Completable

    @Query("select * from Dish")
    fun getAllFromBasket(): Maybe<List<Dish>>

    @Query("update Dish set amount=amount+1")
    fun increaseAmount(): Completable

    @Query("update Dish set amount=amount-1")
    fun decreaseAmount(): Completable

}