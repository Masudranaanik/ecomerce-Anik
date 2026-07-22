package com.example.ecomerce_anik.domain.model

data class CartItem(
    val productId: Int,
    val title: String,
    val price: Double,
    val image: String,
    var quantity: Int
)
