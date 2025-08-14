package com.hadiyarajesh.week2.day3_dependency_injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {
    @Provides
    @Singleton
    @RajeshClass
//    @Named("RajeshClass")
    fun getRajeshPrivateClassInstance(): PrivateClass {
        return PrivateClass.getInstance("Rajesh PrivateClass")
    }

    @Provides
    @Singleton
    @VikasClass
//    @Named("VikasClass")
    fun getVikasPrivateClassInstance(): PrivateClass {
        return PrivateClass.getInstance("Vikas PrivateClass")
    }

//    @Provides
//    fun provideRepoInstance(privateClass: PrivateClass): DiRepository {
//        return DiRepository(privateClass)
//    }

//    @Provides
//    fun provideDiRepository(privateClass: PrivateClass): DiRepository {
//        return DiRepositoryImpl(privateClass)
//    }
}

@Qualifier
annotation class VikasClass

@Qualifier
annotation class RajeshClass

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideDiRepository(impl: DiRepositoryImpl): DiRepository
}
