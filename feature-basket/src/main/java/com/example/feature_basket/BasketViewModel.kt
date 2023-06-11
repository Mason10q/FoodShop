package com.example.feature_basket

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_android.SingleLiveEvent
import com.example.core_db.BasketRepo
import javax.inject.Inject

class BasketViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: BasketRepo


    private val _basketData = SingleLiveEvent<List<Dish>>()
    val basketData: LiveData<List<Dish>> = _basketData

    private val _amountChangedData = MutableLiveData<Unit>()
    val amountChangedData: LiveData<Unit> = _amountChangedData

    fun getAllBasket() = composite.add(
        repository.getAllDishesFromBasket()
            .map { DishMapper.mapListFromTableToEntity(it) }
            .subscribe({
                _basketData.postValue(it)
                _amountChangedData.postValue(Unit)
            }, {})
    )

    fun increaseAmount(dishId: Int) = composite.add(
        repository.increaseAmountOfDish(dishId)
            .subscribe({
                _amountChangedData.postValue(Unit)
            }, {})
    )

    fun decreaseAmount(dishId: Int) = composite.add(
        repository.decreaseAmountOfDish(dishId)
            .subscribe({
                _amountChangedData.postValue(Unit)
            }, {})
    )

    fun deleteFromTable(dishId: Int) = composite.add(
        repository.deleteDishFromBasket(dishId)
            .subscribe({
                _amountChangedData.postValue(Unit)
            }, {})
    )

    override fun onStop() {
        composite.clear()
    }
}