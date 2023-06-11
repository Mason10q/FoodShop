package com.example.feature_basket

import com.example.core_db.DishTable

object DishMapper {

    private fun mapFromTableToEntity(dishTable: DishTable): Dish {
        with(dishTable) {
            return Dish(id, name, price, weight, description, imageUrl, tegs, amount)
        }
    }

    fun mapListFromTableToEntity(dishTableList: List<DishTable>): List<Dish>{
        val result = ArrayList<Dish>()

        for(dishTable in dishTableList){
            result.add(mapFromTableToEntity(dishTable))
        }

        return result
    }

}