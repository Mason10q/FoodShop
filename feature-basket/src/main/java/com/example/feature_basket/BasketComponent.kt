package com.example.feature_basket

import android.content.Context
import com.example.core_android.AndroidModule
import com.example.core_db.BasketDataBaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class, BasketDataBaseModule::class, BasketModule::class])
interface BasketComponent {

    fun inject(viewModel: BasketViewModel)

    fun inject(fragment: BasketFragment)
    companion object{
        fun init(context: Context): BasketComponent = DaggerBasketComponent.builder()
            .androidModule(AndroidModule(context))
            .basketDataBaseModule(BasketDataBaseModule(context))
            .build()
    }

}