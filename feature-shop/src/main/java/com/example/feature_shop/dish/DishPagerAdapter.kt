package com.example.feature_shop.dish

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.abstracttesting.adapter.BaseAdapter
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.category.Category
import com.example.feature_shop.databinding.ItemDishesBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DishPagerAdapter: BaseAdapter<DishPagerItem, ItemDishesBinding>(ItemDishesBinding::inflate) {

    private var onDishClicked = { _: View, _: Dish ->}

    fun setOnDishClick(l: (view: View, item: Dish) -> Unit){
        onDishClicked = l
    }

    override fun bindViews(binding: ItemDishesBinding, item: DishPagerItem, position: Int) {
        binding.root.layoutManager = GridLayoutManager(binding.root.context, 3)

        binding.root.adapter = DishesAdapter().apply {
            ShopComponent.init(binding.root.context).inject(this)
            addItems(item.items)
            setOnDishClick(onDishClicked)
        }
    }

    override fun onViewClicked(view: View, item: DishPagerItem) {}


}