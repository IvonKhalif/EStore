package com.example.estore.remote.repository.product

import com.example.estore.remote.data.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}