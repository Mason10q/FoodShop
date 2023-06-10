package com.example.feature_shop.dish

import com.example.core_network.DishesDto

object DishMapper {

    fun map(dto: DishesDto): List<com.example.core_entities.Dish> {
        val res = ArrayList<com.example.core_entities.Dish>()
        dto.dishes.forEach {
            res.add(
                com.example.core_entities.Dish(
                    name = it.name ?: "",
                    price = it.price ?: 0,
                    weight = it.weight ?: 0,
                    description = it.description ?: "",
                    imageUrl = it.imageUrl ?: "",
                    tegs = it.tegs
                )
            )
        }
        return res
    }

    fun mapByTegs(dishes: List<com.example.core_entities.Dish>, tegs: List<String>): List<List<com.example.core_entities.Dish>>{
        val result = ArrayList<List<com.example.core_entities.Dish>>()

        for(teg in tegs){
            val r = ArrayList<com.example.core_entities.Dish>()

            dishes.forEach{
                if(teg in it.tegs){
                    r.add(it)
                }
            }

            result.add(r)
        }

        return result
    }

}