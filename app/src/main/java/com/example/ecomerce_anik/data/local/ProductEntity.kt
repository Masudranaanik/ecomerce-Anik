package com.example.ecomerce_anik.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Double = 0.0,
    val ratingCount: Int = 0
)
