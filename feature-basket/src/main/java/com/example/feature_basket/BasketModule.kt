package com.example.feature_basket

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class BasketModule {

    @Provides
    fun provideBasketAdapter(picasso: Picasso) = BasketAdapter(picasso)

}