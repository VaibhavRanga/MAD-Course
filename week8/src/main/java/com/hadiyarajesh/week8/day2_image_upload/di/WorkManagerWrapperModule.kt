package com.hadiyarajesh.week8.day2_image_upload.di

import com.hadiyarajesh.week8.day2_image_upload.WorkManagerWrapper
import com.hadiyarajesh.week8.day2_image_upload.WorkManagerWrapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WorkManagerWrapperModule {
    @Binds
    abstract fun bindWorkManagerWrapper(impl: WorkManagerWrapperImpl): WorkManagerWrapper
}
