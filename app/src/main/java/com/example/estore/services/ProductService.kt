package com.example.estore.services

import com.example.estore.remote.data.Product
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}