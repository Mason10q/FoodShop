package com.example.feature_shop.dish

import android.view.View
import com.example.abstracttesting.adapter.BaseAdapter
import com.example.feature_shop.databinding.ItemDishBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DishesAdapter: BaseAdapter<com.example.core_entities.Dish, ItemDishBinding>(ItemDishBinding::inflate) {

    @Inject lateinit var picasso: Picasso

    private var onDishClicked = { _: View, _: com.example.core_entities.Dish ->}

    fun setOnDishClick(l: (view: View, item: com.example.core_entities.Dish) -> Unit){
        onDishClicked = l
    }

    override fun bindViews(binding: ItemDishBinding, item: com.example.core_entities.Dish, position: Int) {
        picasso.load(item.imageUrl).into(binding.dishImage)
        binding.dishName.text = item.name
    }

    override fun onViewClicked(view: View, item: com.example.core_entities.Dish) = onDishClicked(view, item)
}