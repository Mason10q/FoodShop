package com.example.feature_shop.dish

import com.example.core_network.DishesDto

object DishMapper {

    fun map(dto: DishesDto): List<Dish> {
        val res = ArrayList<Dish>()
        dto.dishes.forEach {
            res.add(
                Dish(
                    it.name ?: "",
                    it.price ?: 0,
                    it.weight ?: 0,
                    it.description ?: "",
                    it.imageUrl ?: "",
                    it.tegs
                )
            )
        }
        return res
    }

}