package com.example.feature_basket

import android.content.Context
import com.example.core_android.AndroidModule
import com.example.core_db.BasketDataBaseModule
import dagger.Component

@Component(modules = [AndroidModule::class, BasketDataBaseModule::class])
interface BasketComponent {

    fun inject(adapter: BasketAdapter)
    fun inject(viewModel: BasketViewModel)

    companion object{
        fun init(context: Context): BasketComponent = DaggerBasketComponent.builder()
            .androidModule(AndroidModule(context))
            .build()
    }

}