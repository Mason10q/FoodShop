package com.example.feature_basket

import androidx.lifecycle.ViewModel
import com.example.core_db.BasketRepo
import javax.inject.Inject

class BasketViewModel: ViewModel() {

    @Inject lateinit var repository: BasketRepo

    val totalPrice = 0

}