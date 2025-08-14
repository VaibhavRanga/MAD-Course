package com.hadiyarajesh.week6.day2_networking.di

import com.hadiyarajesh.week6.day2_networking.NetworkingRepository
import com.hadiyarajesh.week6.day2_networking.NetworkingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//    @Provides
//    fun provideNetworkingRepository(photoApi: PhotoApi): NetworkingRepository {
//        return NetworkingRepositoryImpl(photoApi)
//    }
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideNetworkingRepository(impl: NetworkingRepositoryImpl): NetworkingRepository
}
