package com.hadiyarajesh.week8.day2_image_upload.di

import com.hadiyarajesh.week8.day2_image_upload.ImageUploadRepository
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageUploadRepositoryModule {
    @Binds
    abstract fun provideImageUploadRepository(impl: ImageUploadRepositoryImpl): ImageUploadRepository
}
