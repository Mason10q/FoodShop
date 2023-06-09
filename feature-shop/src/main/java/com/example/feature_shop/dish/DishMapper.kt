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

    fun mapByTegs(dishes: List<Dish>, tegs: List<String>): List<List<Dish>>{
        val result = ArrayList<List<Dish>>()

        for(teg in tegs){
            val r = ArrayList<Dish>()

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