package com.hadiyarajesh.week8.day1_background_work.di

import com.hadiyarajesh.week8.day1_background_work.BackgroundWorkRepository
import com.hadiyarajesh.week8.day1_background_work.BackgroundWorkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideBackgroundWorkRepository(impl: BackgroundWorkRepositoryImpl): BackgroundWorkRepository
}