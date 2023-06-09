package com.example.core_network

import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("сategories")
    val categories: List<CategoryDto>
)

data class CategoryDto(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = ""
)