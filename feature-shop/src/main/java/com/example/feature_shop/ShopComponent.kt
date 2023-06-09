package com.example.feature_shop

import android.content.Context
import com.example.core_android.AndroidModule
import com.example.core_network.ApiModule
import com.example.feature_shop.category.CategoryAdapter
import com.example.feature_shop.category.CategoryViewModel
import dagger.Component

@Component(modules = [AndroidModule::class, ApiModule::class])
interface ShopComponent {

    fun inject(viewModel: CategoryViewModel)
    fun inject(adapter: CategoryAdapter)

    companion object{
        fun init(context: Context): ShopComponent = DaggerShopComponent.builder()
            .androidModule(AndroidModule(context))
            .build()
    }

}