package dev.soul.effectivemobtest.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.soul.data.remote.MainService
import dev.soul.data.repository.MainRepositoryImpl
import dev.soul.domain.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(mainService: MainService): MainRepository {
        return MainRepositoryImpl(mainService)
    }
}