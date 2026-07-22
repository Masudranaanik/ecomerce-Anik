package com.example.ecomerce_anik.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalProductDao {
    @Query("SELECT * FROM local_products ORDER BY id DESC")
    fun getAllLocalProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("DELETE FROM local_products WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}
