package com.example.feature_shop.category

import com.example.core_network.CategoriesDto

object CategoryMapper {

    fun map(dto: CategoriesDto): List<Category> {
        val res = ArrayList<Category>()
        dto.categories.forEach {
            res.add(Category(it.name ?: "", it.imageUrl ?: ""))
        }
        return res
    }

}