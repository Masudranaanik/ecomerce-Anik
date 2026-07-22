package com.example.ecomerce_anik.data.repository

import com.example.ecomerce_anik.data.local.*
import com.example.ecomerce_anik.data.remote.ApiService
import com.example.ecomerce_anik.domain.model.CartItem
import com.example.ecomerce_anik.domain.model.Product
import com.example.ecomerce_anik.domain.model.Rating
import com.example.ecomerce_anik.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val cartDao: CartDao,
    private val wishlistDao: WishlistDao,
    private val localProductDao: LocalProductDao
) : ProductRepository {

    private val dummyProducts = listOf(
        Product(1, "Premium Blue Jeans", 45.99, "High-quality denim jeans for everyday wear.", "Men's Clothing", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", Rating(4.5, 120)),
        Product(2, "Cotton T-Shirt", 19.99, "Soft and breathable cotton t-shirt.", "Men's Clothing", "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg", Rating(4.2, 85)),
        Product(3, "Leather Jacket", 89.99, "Stylish faux leather jacket for all seasons.", "Men's Clothing", "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg", Rating(4.8, 45)),
        Product(4, "Silver Bracelet", 25.50, "Elegant silver-plated bracelet for women.", "Jewelry", "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg", Rating(4.0, 30)),
        Product(5, "Gold Ring", 150.00, "14k solid gold ring with a minimalist design.", "Jewelry", "https://fakestoreapi.com/img/61sbMiTPuGL._AC_UL640_QL65_ML3_.jpg", Rating(4.9, 15)),
        Product(6, "Wireless Headphones", 59.99, "Noise-cancelling over-ear wireless headphones.", "Electronics", "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", Rating(4.6, 210)),
        Product(7, "Smart Watch", 129.99, "Waterproof smart watch with fitness tracking.", "Electronics", "https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_.jpg", Rating(4.3, 150)),
        Product(8, "Designer Handbag", 75.00, "Spacious and fashionable leather handbag.", "Women's Clothing", "https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg", Rating(4.7, 60))
    )

    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val response = api.getAllProducts()
            if (response.isEmpty()) Result.success(dummyProducts) else Result.success(response)
        } catch (e: Exception) {
            Result.success(dummyProducts) // Fallback to dummy data on internet error
        }
    }

    override fun getLocalProducts(): Flow<List<Product>> {
        return localProductDao.getAllLocalProducts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertLocalProduct(product: Product) {
        localProductDao.insertProduct(product.toLocalEntity())
    }

    override suspend fun getProductById(id: Int): Result<Product> {
        return try {
            Result.success(api.getProductById(id))
        } catch (e: Exception) {
            val dummy = dummyProducts.find { it.id == id }
            if (dummy != null) Result.success(dummy) else Result.failure(e)
        }
    }

    override suspend fun getCategories(): Result<List<String>> {
        return try {
            val response = api.getCategories()
            if (response.isEmpty()) Result.success(listOf("Men's Clothing", "Jewelry", "Electronics", "Women's Clothing")) 
            else Result.success(response)
        } catch (e: Exception) {
            Result.success(listOf("Men's Clothing", "Jewelry", "Electronics", "Women's Clothing"))
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return try {
            val response = api.getProductsByCategory(category)
            if (response.isEmpty()) Result.success(dummyProducts.filter { it.category == category })
            else Result.success(response)
        } catch (e: Exception) {
            Result.success(dummyProducts.filter { it.category == category })
        }
    }

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getAllCartItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToCart(product: Product) {
        cartDao.insertCartItem(product.toCartEntity())
    }

    override suspend fun removeFromCart(cartItem: CartItem) {
        cartDao.deleteCartItem(cartItem.toEntity())
    }

    override suspend fun updateCartQuantity(id: Int, quantity: Int) {
        cartDao.updateQuantity(id, quantity)
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }

    override fun getWishlistItems(): Flow<List<Product>> {
        return wishlistDao.getAllWishlistItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun toggleWishlist(product: Product) {
        if (wishlistDao.isFavourite(product.id)) {
            wishlistDao.deleteWishlistItem(product.toWishlistEntity())
        } else {
            wishlistDao.insertWishlistItem(product.toWishlistEntity())
        }
    }

    override suspend fun isFavourite(id: Int): Boolean {
        return wishlistDao.isFavourite(id)
    }
}

// Mappers
fun CartEntity.toDomain() = CartItem(id, title, price, image, quantity)
fun CartItem.toEntity() = CartEntity(productId, title, price, image, quantity)
fun Product.toCartEntity() = CartEntity(id, title, price, image, 1)

fun WishlistEntity.toDomain() = Product(id, title, price, "", "", image, Rating(0.0, 0))
fun Product.toWishlistEntity() = WishlistEntity(id, title, price, image)

fun ProductEntity.toDomain() = Product(id, title, price, description, category, image, Rating(rating, ratingCount))
fun Product.toLocalEntity() = ProductEntity(
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = rating.rate,
    ratingCount = rating.count
)
