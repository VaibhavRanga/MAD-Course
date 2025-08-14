package com.hadiyarajesh.week9.day1_app_localization.di

import com.hadiyarajesh.week9.day1_app_localization.settings.SettingsRepository
import com.hadiyarajesh.week9.day1_app_localization.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindWorkManagerWrapper(impl: SettingsRepositoryImpl): SettingsRepository
}
