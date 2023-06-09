package com.example.core_network

import com.google.gson.annotations.SerializedName

data class DishesDto(
    @SerializedName("dishes")
    val dishes: List<DishDto>
)

data class DishDto(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("price")
    val price: Int? = 0,
    @SerializedName("weight")
    val weight: Int? = 0,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("tegs")
    val tegs: List<String>
)