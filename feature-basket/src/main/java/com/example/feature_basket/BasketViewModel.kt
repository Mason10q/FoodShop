package com.example.feature_basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_android.BaseViewModel
import com.example.core_db.BasketRepo
import com.example.core_entities.Dish
import javax.inject.Inject

class BasketViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: BasketRepo

    private val _basketData = MutableLiveData<List<Dish>>()
    val basketData: LiveData<List<Dish>> = _basketData

    fun getAllBasket() = composite.add(repository.getAllDishesFromBasket()
        .subscribe({
            _basketData.postValue(it)
        }, {})
    )
}