package com.example.estore.utils.modules

import com.example.estore.services.ProductService
import com.example.estore.utils.NetworkUtil
import com.example.estore.utils.NetworkUtil.BASE_URL
import org.koin.dsl.module

val NetworkModule = module {
    single { NetworkUtil .buildClient(get()) }
    single { NetworkUtil.buildService<ProductService>(BASE_URL, get()) }
}