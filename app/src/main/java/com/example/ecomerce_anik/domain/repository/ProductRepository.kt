package com.example.ecomerce_anik.domain.repository

import com.example.ecomerce_anik.domain.model.CartItem
import com.example.ecomerce_anik.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    // Products
    suspend fun getProducts(): Result<List<Product>>
    fun getLocalProducts(): Flow<List<Product>>
    suspend fun insertLocalProduct(product: Product)
    suspend fun getProductById(id: Int): Result<Product>
    suspend fun getCategories(): Result<List<String>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>

    // Cart
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(product: Product)
    suspend fun removeFromCart(cartItem: CartItem)
    suspend fun updateCartQuantity(id: Int, quantity: Int)
    suspend fun clearCart()

    // Wishlist
    fun getWishlistItems(): Flow<List<Product>>
    suspend fun toggleWishlist(product: Product)
    suspend fun isFavourite(id: Int): Boolean
}
