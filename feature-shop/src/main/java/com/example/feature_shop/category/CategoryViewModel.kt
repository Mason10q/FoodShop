package com.example.feature_shop.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseViewModel
import com.example.core_network.Api
import com.example.feature_shop.category.Category
import com.example.feature_shop.category.CategoryMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CategoryViewModel : BaseViewModel() {

    @Inject
    lateinit var api: Api

    private val _categoriesData = MutableLiveData<List<Category>>()
    val categoriesData: LiveData<List<Category>> = _categoriesData


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



}