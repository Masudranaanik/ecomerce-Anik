package com.example.ecomerce_anik.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_items")
data class WishlistEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val image: String
)
