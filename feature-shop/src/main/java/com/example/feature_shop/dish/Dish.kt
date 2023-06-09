package com.example.feature_shop.dish

data class Dish(
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String,
    val tegs: List<String>
)