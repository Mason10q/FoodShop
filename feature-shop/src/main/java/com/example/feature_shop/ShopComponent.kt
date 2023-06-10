package com.example.feature_shop

import android.content.Context
import com.example.core_android.AndroidModule
import com.example.core_db.BasketDataBaseModule
import com.example.core_network.ApiModule
import com.example.feature_shop.category.CategoryAdapter
import com.example.feature_shop.category.CategoryFragment
import com.example.feature_shop.category.CategoryViewModel
import com.example.feature_shop.dish.DialogViewModel
import com.example.feature_shop.dish.DishDialog
import com.example.feature_shop.dish.DishFragment
import com.example.feature_shop.dish.DishViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class, ApiModule::class, BasketDataBaseModule::class, ShopModule::class])
interface ShopComponent {

    fun inject(viewModel: DishViewModel)
    fun inject(viewModel: CategoryViewModel)
    fun inject(adapter: CategoryAdapter)
    fun inject(dialog: DishDialog)
    fun inject(viewModel: DialogViewModel)

    fun inject(fragment: DishFragment)

    fun inject(fragment: CategoryFragment)

    companion object{
        fun init(context: Context): ShopComponent = DaggerShopComponent.builder()
            .androidModule(AndroidModule(context))
            .apiModule(ApiModule())
            .basketDataBaseModule(BasketDataBaseModule(context))
            .shopModule(ShopModule())
            .build()
    }

}