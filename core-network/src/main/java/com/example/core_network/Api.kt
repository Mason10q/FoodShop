package com.example.core_network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface Api {

    @GET("058729bd-1402-4578-88de-265481fd7d54/")
    fun getCategories(): Single<CategoriesDto>

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b/")
    fun getDishes(): Single<DishesDto>
}