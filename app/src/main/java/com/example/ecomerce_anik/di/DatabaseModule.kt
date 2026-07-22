package com.example.ecomerce_anik.di

import android.content.Context
import androidx.room.Room
import com.example.ecomerce_anik.data.local.AppDatabase
import com.example.ecomerce_anik.data.local.CartDao
import com.example.ecomerce_anik.data.local.WishlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ecom_db"
        ).build()
    }

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    @Provides
    fun provideWishlistDao(db: AppDatabase): WishlistDao = db.wishlistDao()
}
