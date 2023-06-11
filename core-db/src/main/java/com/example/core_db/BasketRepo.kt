package com.example.core_db

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BasketRepo(private val dao: BasketDao) {

    fun addDishToBasket(dish: DishTable) = dao.insertDish(dish)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getAllDishesFromBasket() = dao.getAllFromBasket()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun increaseAmountOfDish(id: Int) = dao.increaseAmount(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun decreaseAmountOfDish(id: Int) = dao.decreaseAmount(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun deleteDishFromBasket(id: Int) = dao.deleteFromBasket(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun getFromBasketByName(name: String) = dao.getByDishByName(name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun checkIfInBasket(name: String) = dao.checkIfDishInBasket(name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getAmountOfDishFromBasket(id: Int) = dao.getAmount(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


}