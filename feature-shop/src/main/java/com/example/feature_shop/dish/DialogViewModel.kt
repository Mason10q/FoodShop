package com.example.feature_shop.dish

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_db.BasketRepo
import javax.inject.Inject

class DialogViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: BasketRepo

    private val _checkData = MutableLiveData<Boolean>()
    val checkData: LiveData<Boolean> = _checkData

    fun addToBasket(dish: Dish) = composite.add(
        repository.addDishToBasket(DishMapper.mapDishEntityToTable(dish))
            .subscribe()
    )

    fun checkIfInBasket(name: String) = composite.add(
        repository.checkIfInBasket(name)
            .subscribe({
                _checkData.postValue(it)
                Log.d("dialogError", "viewModel response $it")
            }, {
                Log.e("dialogError", "viewModel", it)
            })
    )

}