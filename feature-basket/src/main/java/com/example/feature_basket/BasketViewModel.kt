package com.example.feature_basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_db.BasketRepo
import javax.inject.Inject

class BasketViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: BasketRepo

    private val _basketData = MutableLiveData<List<Dish>>()
    val basketData: LiveData<List<Dish>> = _basketData

    fun getAllBasket() = composite.add(repository.getAllDishesFromBasket()
        .subscribe({
            _basketData.postValue(DishMapper.mapListFromTableToEntity(it))
        }, {})
    )
}