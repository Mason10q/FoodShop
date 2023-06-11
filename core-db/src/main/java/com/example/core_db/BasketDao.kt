package com.example.core_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDish(dish: DishTable): Completable

    @Query("select * from DishTable")
    fun getAllFromBasket(): Maybe<List<DishTable>>

    @Query("update DishTable set amount=amount+1 where id=:id")
    fun increaseAmount(id: Int): Completable

    @Query("update DishTable set amount=amount-1 where id=:id")
    fun decreaseAmount(id: Int): Completable

    @Query("delete from DishTable where id = :id")
    fun deleteFromBasket(id: Int): Completable

    @Query("select * from DishTable where name = :name")
    fun getByDishByName(name: String): Single<DishTable>

    @Query("select exists(select * from DishTable where name = :name)")
    fun checkIfDishInBasket(name: String): Single<Boolean>

    @Query("select amount from DishTable where id = :id")
    fun getAmount(id: Int): Single<Int>

}