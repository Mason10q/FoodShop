package com.example.feature_shop.dish

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.abstracttesting.adapter.BaseAdapter
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.ItemDishesBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DishPagerAdapter: BaseAdapter<DishPagerItem, ItemDishesBinding>(ItemDishesBinding::inflate) {

    override fun bindViews(binding: ItemDishesBinding, item: DishPagerItem, position: Int) {
        binding.root.layoutManager = GridLayoutManager(binding.root.context, 3)

        binding.root.adapter = DishesAdapter().apply {
            ShopComponent.init(binding.root.context).inject(this)
            addItems(item.items)
        }
    }

    override fun onViewClicked(view: View, item: DishPagerItem) {}


}