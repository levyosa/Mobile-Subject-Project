package com.example.trabalhofinaldm.api


import com.example.trabalhofinaldm.models.Product
import retrofit2.Call
import retrofit2.http.GET
import kotlin.collections.List

interface ProductService {
    @GET("products")
    fun getAll(): Call<List<Product>>

    @GET("books/{id}")
    fun get(): Call<Product>


}