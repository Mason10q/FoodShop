package com.example.core_db

import com.example.core_entities.Dish
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BasketRepo(private val dao: BasketDao) {

    fun addDishToBasket(dish: Dish) = dao.insertDish(dish)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getAllDishesFromBasket() = dao.getAllFromBasket()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun increaseAmountOfDish() = dao.increaseAmount()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun decreaseAmountOfDish() = dao.decreaseAmount()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}