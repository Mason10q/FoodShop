package com.example.feature_shop.dish

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_network.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DishViewModel: BaseViewModel() {

    @Inject lateinit var api: Api

    private val _dishesData = MutableLiveData<List<Dish>>()
    val dishesData: LiveData<List<Dish>> = _dishesData

    val tabNames = listOf("Все меню", "Салаты", "С рисом", "С рыбой")
    val states = arrayOf(intArrayOf(android.R.attr.state_selected), intArrayOf(-android.R.attr.state_selected))
    val colors = intArrayOf(Color.WHITE, Color.BLACK)

    fun getDishes() = composite.add(api.getDishes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { DishMapper.map(it) }
        .subscribe({
            _dishesData.postValue(it)
        }, {
            _error.postValue(it.message)
        })
    )

}