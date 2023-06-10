package com.example.feature_basket

import android.view.View
import com.example.abstracttesting.adapter.BaseAdapter
import com.example.core_entities.Dish
import com.example.feature_basket.databinding.ItemBasketBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class BasketAdapter: BaseAdapter<Dish, ItemBasketBinding>(ItemBasketBinding::inflate) {

    @Inject lateinit var picasso: Picasso

    override fun bindViews(binding: ItemBasketBinding, item: Dish, position: Int) {
        with(binding) {
            picasso.load(item.imageUrl).into(dishImage)
            dishName.text = item.name
            dishPrice.text = item.price.toString()
            dishWeight.text = item.weight.toString()
        }
    }

    override fun onViewClicked(view: View, item: Dish) {}

}