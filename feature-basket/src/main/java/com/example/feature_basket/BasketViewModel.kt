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


    fun getAllBasket() = composite.add(
        repository.getAllDishesFromBasket()
            .map { DishMapper.mapListFromTableToEntity(it) }
            .subscribe({
                Log.d("basket", "request")
                _basketData.postValue(it)
            }, {})
    )

    override fun onStop() {
        composite.clear()
    }
}