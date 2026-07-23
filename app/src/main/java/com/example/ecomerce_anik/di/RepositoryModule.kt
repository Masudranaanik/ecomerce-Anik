package com.example.ecomerce_anik.di

import com.example.ecomerce_anik.data.repository.AuthRepositoryImpl
import com.example.ecomerce_anik.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: com.example.ecomerce_anik.data.repository.ProductRepositoryImpl
    ): com.example.ecomerce_anik.domain.repository.ProductRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
