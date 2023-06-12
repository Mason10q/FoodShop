package com.example.feature_basket

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core_android.BaseAdapter
import com.example.feature_basket.databinding.ItemBasketBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class BasketAdapter @Inject constructor(
    private val picasso: Picasso
): BaseAdapter<Dish, ItemBasketBinding>(ItemBasketBinding::inflate) {

    private var controller: DishAmountController = object: DishAmountController{
        override fun increase(id: Int) {}
        override fun decrease(id: Int) {}
        override fun delete(id: Int) {}
    }

    override fun bindViews(binding: ItemBasketBinding, item: Dish, position: Int) {
        with(binding) {
            picasso.load(item.imageUrl).into(dishImage)
            dishName.text = item.name
            dishPrice.text = item.price.toString()
            dishWeight.text = item.weight.toString()
            amountCounter.text = item.amount.toString()

            increaseBtn.setOnClickListener{
                controller.increase(item.id)
                ++item.amount
                amountCounter.text = item.amount.toString()
            }

            decreaseBtn.setOnClickListener{
                if(--item.amount != 0){
                    controller.decrease(item.id)
                    amountCounter.text = item.amount.toString()
                } else{
                    controller.delete(item.id)
                    removeItem(item, position)
                }
            }
        }
    }

    override fun onViewClicked(view: View, item: Dish) {}

    fun setDishAmountCallback(controller: DishAmountController){
        this.controller = controller
    }

    fun countFullPrice(): Int{
        var sum = 0

        for(item in items){
            sum += (item.price * item.amount)
        }

        return sum
    }

}