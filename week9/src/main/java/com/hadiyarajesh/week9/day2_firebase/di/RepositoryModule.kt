package com.hadiyarajesh.week9.day2_firebase.di

import com.hadiyarajesh.week9.day2_firebase.authentication.AuthenticationRepository
import com.hadiyarajesh.week9.day2_firebase.authentication.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideImageUploadRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository
}
