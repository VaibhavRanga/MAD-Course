package com.hadiyarajesh.week7.day1_pagination.di

import com.hadiyarajesh.week7.day1_pagination.PaginationRepository
import com.hadiyarajesh.week7.day1_pagination.PaginationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideNetworkingRepository(impl: PaginationRepositoryImpl): PaginationRepository
}
