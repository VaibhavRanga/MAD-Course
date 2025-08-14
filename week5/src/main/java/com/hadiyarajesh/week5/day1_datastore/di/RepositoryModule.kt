package com.hadiyarajesh.week5.day1_datastore.di

import com.hadiyarajesh.week5.day1_datastore.datastore.DatastoreRepository
import com.hadiyarajesh.week5.day1_datastore.datastore.DatastoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(impl: DatastoreRepositoryImpl): DatastoreRepository
}
