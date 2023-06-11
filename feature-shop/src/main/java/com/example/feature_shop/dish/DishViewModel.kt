package com.example.feature_shop.dish

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_android.RxJavaUtil
import com.example.core_network.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DishViewModel: BaseViewModel() {

    @Inject lateinit var api: Api

    private val _dishesData = MutableLiveData<List<Dish>>()
    val dishesData: LiveData<List<Dish>> = _dishesData

    val tabNames = listOf("Все меню", "Салаты", "С рисом", "С рыбой")

    fun getDishes() = composite.add(api.getDishes()
        .compose(RxJavaUtil.SingleTransUtil())
        .map { DishMapper.map(it) }
        .subscribe({
            _dishesData.postValue(it)
        }, {
            _error.postValue(it.message)
        })
    )

}