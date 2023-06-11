package com.example.feature_shop.category

import android.view.View
import com.example.core_android.BaseAdapter
import com.example.feature_shop.databinding.ItemCategoryBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CategoryAdapter @Inject constructor(
    private val picasso: Picasso
) : BaseAdapter<Category, ItemCategoryBinding>(ItemCategoryBinding::inflate) {


    private var onCategoryClick = { _: View, _: Category -> }

    fun setOnCategoryClickListener(listener: (view: View, item: Category) -> Unit) {
        onCategoryClick = listener
    }

    override fun bindViews(binding: ItemCategoryBinding, item: Category, position: Int) {
        binding.categoryName.text = item.name
        picasso.load(item.imageUrl).into(binding.root)
    }

    override fun onViewClicked(view: View, item: Category) = onCategoryClick(view, item)


}