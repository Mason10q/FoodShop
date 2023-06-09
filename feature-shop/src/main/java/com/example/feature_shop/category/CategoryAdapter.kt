package com.example.feature_shop.category

import android.view.View
import com.example.abstracttesting.adapter.BaseAdapter
import com.example.feature_shop.databinding.ItemCategoryBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CategoryAdapter: BaseAdapter<Category, ItemCategoryBinding>(ItemCategoryBinding::inflate) {

    @Inject lateinit var picasso: Picasso

    override fun bindViews(binding: ItemCategoryBinding, item: Category, position: Int) {
        binding.name.text = item.name
        picasso.load(item.imageUrl).into(binding.root)
    }

    override fun onViewClicked(view: View, item: Category) {

    }

}