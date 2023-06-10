package com.example.core_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_entities.Dish
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDish(dish: Dish): Completable

    @Query("select * from Dish")
    fun getAllFromBasket(): Maybe<List<Dish>>

    @Query("update Dish set amount=amount+1")
    fun increaseAmount(): Completable

    @Query("update Dish set amount=amount-1")
    fun decreaseAmount(): Completable

    @Query("delete from Dish where id = :id")
    fun deleteFromBasket(id: Int): Completable

    @Query("select * from Dish where name = :name")
    fun getByDishByName(name: String): Single<Dish>

    @Query("select exists(select * from Dish where name = :name)")
    fun checkIfDishInBasket(name: String): Single<Boolean>

    @Query("select amount from Dish where id = :id")
    fun getAmount(id: Int): Single<Int>

}