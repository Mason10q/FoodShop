package com.example.feature_basket

import java.io.Serializable

data class Dish(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String,
    val tegs: List<String>,
    var amount: Int
): Serializable