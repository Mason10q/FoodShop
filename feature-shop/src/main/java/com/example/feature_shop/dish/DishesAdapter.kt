package com.example.feature_shop.dish

import android.view.View
import com.example.abstracttesting.adapter.BaseAdapter
import com.example.core_entities.Dish
import com.example.feature_shop.databinding.ItemDishBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DishesAdapter @Inject constructor(
    private val picasso: Picasso
) : BaseAdapter<Dish, ItemDishBinding>(ItemDishBinding::inflate) {

    private var onDishClicked = { _: View, _: Dish -> }

    fun setOnDishClick(l: (view: View, item: Dish) -> Unit) {
        onDishClicked = l
    }

    override fun bindViews(binding: ItemDishBinding, item: Dish, position: Int) {
        picasso.load(item.imageUrl).into(binding.dishImage)
        binding.dishName.text = item.name
    }

    override fun onViewClicked(view: View, item: Dish) = onDishClicked(view, item)
}