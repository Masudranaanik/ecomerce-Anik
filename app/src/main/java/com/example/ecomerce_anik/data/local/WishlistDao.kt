package com.example.ecomerce_anik.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {
    @Query("SELECT * FROM wishlist_items")
    fun getAllWishlistItems(): Flow<List<WishlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(item: WishlistEntity)

    @Delete
    suspend fun deleteWishlistItem(item: WishlistEntity)

    @Query("SELECT EXISTS(SELECT * FROM wishlist_items WHERE id = :id)")
    suspend fun isFavourite(id: Int): Boolean
}
