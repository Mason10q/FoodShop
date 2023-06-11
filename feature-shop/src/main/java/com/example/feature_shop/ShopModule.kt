package com.example.feature_shop

import com.example.feature_shop.category.CategoryAdapter
import com.example.feature_shop.dish.DishPagerAdapter
import com.example.feature_shop.dish.DishesAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class ShopModule {

    @Provides
    fun provideCategoryAdapter(picasso: Picasso) = CategoryAdapter(picasso)

    @Provides
    fun provideDishesAdapter(picasso: Picasso) = DishesAdapter(picasso)

    @Provides
    fun provideDishPagerAdapter(picasso: Picasso) = DishPagerAdapter(picasso)

}