package com.example.ecomerce_anik.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartEntity::class, WishlistEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun wishlistDao(): WishlistDao
}
