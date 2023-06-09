package com.example.feature_shop.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_network.Api
import com.example.feature_shop.dish.Dish
import com.example.feature_shop.dish.DishMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CategoryViewModel : BaseViewModel() {

    @Inject
    lateinit var api: Api

    private val _categoriesData = MutableLiveData<List<Category>>()
    val categoriesData: LiveData<List<Category>> = _categoriesData

    private val _dishesData = MutableLiveData<List<Dish>>()
    val dishesData: LiveData<List<Dish>> = _dishesData


    fun getCategories() = composite.add(api.getCategories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { CategoryMapper.map(it) }
        .subscribe({
            _categoriesData.postValue(it)
        }, {
            _error.postValue(it.message)
        })
    )


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