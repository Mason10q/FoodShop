package com.example.feature_shop

import android.content.Context
import com.example.core_android.AndroidModule
import dagger.Component
import javax.inject.Inject

@Component(modules = [AndroidModule::class])
interface ShopComponent {

    fun inject(adapter: CategoryAdapter)

    companion object{
        fun init(context: Context): ShopComponent = DaggerShopComponent.builder()
            .androidModule(AndroidModule(context))
            .build()
    }

}